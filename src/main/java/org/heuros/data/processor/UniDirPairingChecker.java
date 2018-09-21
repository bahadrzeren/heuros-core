package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.data.repo.DataRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.LegView;
import org.heuros.data.model.Pair;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class UniDirPairingChecker implements Callable<Boolean> {

	private static Logger logger = Logger.getLogger(UniDirPairingChecker.class);

	private int hbNdx = -1;

	public UniDirPairingChecker(int hbNdx) {
		this.hbNdx = hbNdx;
	}

	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInHours = 0;
	private List<Duty> duties = null;
	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;

	public UniDirPairingChecker setMaxIdleTimeInAPairInHours(int maxIdleTimeInAPairInHours) {
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		return this;
	}

	public UniDirPairingChecker setMaxPairingLengthInHours(int maxPairingLengthInHours) {
		this.maxPairingLengthInHours = maxPairingLengthInHours;
		return this;
	}

	public UniDirPairingChecker setDutyRepository(DataRepository<Duty> dutyRepository) {
		this.duties = dutyRepository.getModels();
		return this;
	}

	public UniDirPairingChecker setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	public UniDirPairingChecker setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	public UniDirPairingChecker setDutyIndexByDepAirportNdxBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
		return this;
	}

	@Override
	public Boolean call() {

		Pair p = Pair.newInstance(this.hbNdx);

    	int totNumOfPairingsPossible = 0;

    	for (int i = 0; i < this.duties.size(); i++) {
	    	int numOfPairingsPossible = 0;
	    	Duty d = this.duties.get(i);
	    	if (d.isHbDep(this.hbNdx)
	    			&& d.isValid(this.hbNdx)) {
	    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
	    			this.pairRuleContext.getAggregatorProxy().append(p, d);
	    			if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
	    				if (p.isComplete(this.hbNdx)) {
	    					/*
	    					 * Set validated and save!
	    					 */
	    					numOfPairingsPossible++;
	    				}
	    			}
	    			if (d.isNonHbArr(this.hbNdx)
	    					&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
	    				numOfPairingsPossible += this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg());
	    			}
	    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
	    		}
		    	logger.info(d.getFirstLeg() + " - " + numOfPairingsPossible + " number of pairings is possible.");
		    	totNumOfPairingsPossible += numOfPairingsPossible;
	    	}
		}

		logger.info(totNumOfPairingsPossible + " total number of pairings is checked.");

		return true;
	}

	private int examinePairFW(Pair p, Duty fd, Duty ld, LegView fl, LegView ll) {

    	int numOfPairingsPossible = 0;

		Duty[] nextDuties = null;

		int lastAirportNdx = ll.getArrAirport().getNdx();

		int hourCounter = 0;
		LocalDateTime nextBriefTime = ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter);

		while (true) {
			nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(lastAirportNdx, nextBriefTime);
			if ((nextDuties != null)
					&& (nextDuties.length > 0)) {
				for (Duty nd: nextDuties) {
					if (nd.isValid(this.hbNdx)) {
						if (this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, ld, nd)) {
							if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, nd, true)) {
								pairRuleContext.getAggregatorProxy().append(p, nd);

				    			if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
				    				if (p.isComplete(this.hbNdx)) {
				    					/*
				    					 * Set validated and save!
				    					 */
				    					numOfPairingsPossible++;
				    				}
				    			}

				    			if (nd.isNonHbArr(this.hbNdx)
				    					&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    				numOfPairingsPossible += this.examinePairFW(p, fd, nd, fl, nd.getLastLeg());
				    			}

								pairRuleContext.getAggregatorProxy().removeLast(p);
							}
						}
					}
				}
			}
			hourCounter++;
			nextBriefTime = ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
			if (hourCounter > this.maxIdleTimeInAPairInHours)
				break;
			if (ChronoUnit.HOURS.between(fd.getBriefTime(this.hbNdx), ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter)) > this.maxPairingLengthInHours)
				break;
		}

		return numOfPairingsPossible;
	}

}
