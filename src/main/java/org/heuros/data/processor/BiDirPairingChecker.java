package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.data.repo.DataRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.data.model.Pair;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class BiDirPairingChecker implements Callable<Boolean> {

	private static Logger logger = Logger.getLogger(BiDirPairingChecker.class);

	private int hbNdx = -1;

	public BiDirPairingChecker(int hbNdx) {
		this.hbNdx = hbNdx;
	}

	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInHours = 0;
	private List<Leg> legs = null;
	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;
	private OneDimIndexInt<Duty> dutyIndexByLegNdx = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime= null;

	public BiDirPairingChecker setMaxIdleTimeInAPairInHours(int maxIdleTimeInAPairInHours) {
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		return this;
	}

	public BiDirPairingChecker setMaxPairingLengthInHours(int maxPairingLengthInHours) {
		this.maxPairingLengthInHours = maxPairingLengthInHours;
		return this;
	}

	public BiDirPairingChecker setLegRepository(DataRepository<Leg> legRepository) {
		this.legs = legRepository.getModels();
		return this;
	}

	public BiDirPairingChecker setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	public BiDirPairingChecker setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	public BiDirPairingChecker setDutyIndexByLegNdx(OneDimIndexInt<Duty> dutyIndexByLegNdx) {
		this.dutyIndexByLegNdx = dutyIndexByLegNdx;
		return this;
	}

	public BiDirPairingChecker setDutyIndexByDepAirportNdxBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
		return this;
	}

	public BiDirPairingChecker setDutyIndexByArrAirportNdxNextBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime) {
		this.dutyIndexByArrAirportNdxNextBrieftime = dutyIndexByArrAirportNdxNextBrieftime;
		return this;
	}

	private void setHasPairingFlag(Pair p) {
if (!p.isComplete(this.hbNdx))
logger.error("Pairing must be complete!");
//if (p.getNumOfDuties() == 3)
//logger.info("Pairing with 3 duties!");

		for (int i = 0; i < p.getNumOfDuties(); i++) {
			DutyView d = p.getDuties().get(i);
			for (int j = 0; j < d.getNumOfLegs(); j++) {
				LegView lv = d.getLegs().get(j);
				Leg l = this.legs.get(lv.getNdx());
				if (p.getNumOfDuties() == 1) {
					l.setHasHbDepArrDutyPair(this.hbNdx, true);
				} else
					if (i == 0) {
						l.setHasHbDepDutyPair(this.hbNdx, true);
					} else
						if (i == p.getNumOfDuties() - 1) {
							l.setHasHbArrDutyPair(this.hbNdx, true);
						} else
							l.setHasNonHbDutyPair(this.hbNdx, true);
			}
		}
	}

	@Override
	public Boolean call() {

		Pair p = Pair.newInstance(this.hbNdx);

    	for (int li = 4007; li < this.legs.size(); li++) {
    		Leg l = this.legs.get(li);

            if (l.isCover()) {
            	int pairingFound = 0;

if (l.getNdx() == 4007)
System.out.println(pairingFound);


            	Duty[] ds = this.dutyIndexByLegNdx.getArray(l.getNdx());

            	if ((ds != null)
            			&& (ds.length > 0)) {
    			
            		for (Duty d: ds) {

            			if (d.isValid(this.hbNdx)) {

//if ((l.getNdx() == 1017) && (d.getNdx() == 7045))
//System.out.println(pairingFound);

	    					/*
	    					 * HB dep and arr.
	    					 */
				    		if ((!l.hasHbDepArrDutyPair(this.hbNdx))
				    				&& d.isHbDep(this.hbNdx)
				    				&& d.isHbArr(this.hbNdx)) {
				    			if ((pairingFound & 1) > 0)
				    				logger.error("Pairing is already found before!");
					    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
						    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
						    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
						    			if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
						    				if (p.isComplete(this.hbNdx)) {
						    					/*
						    					 * Set related leg flags!
						    					 */
						    					this.setHasPairingFlag(p);
					    						pairingFound |= 1;
						    				} else
						    					logger.error("Pairing " + d + " must be complete!");
						    			}
						    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
						    		}
					    		}	    			
				    		} else
		    					/*
		    					 * HB dep only.
		    					 */
					    		if ((!l.hasHbDepDutyPair(this.hbNdx))
					    				&& d.isHbDep(this.hbNdx)
					    				&& d.isNonHbArr(this.hbNdx)) {
					    			if ((pairingFound & (1 << 1)) > 0)
					    				logger.error("Pairing is already found before!");
						    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
							    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
							    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
							    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
							    				if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false)) {
							    					/*
							    					 * Set related leg flags!
							    					 */
							    					pairingFound |= (1 << 1);
							    				}
							    			}
							    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
							    		}
						    		}
						    	} else
			    					/*
			    					 * Non HB dep and arr.
			    					 */
						    		if ((!l.hasNonHbDutyPair(this.hbNdx))
						    				&& d.isNonHbDep(this.hbNdx)
						    				&& d.isNonHbArr(this.hbNdx)) {
						    			if ((pairingFound & (1 << 2)) > 0)
						    				logger.error("Pairing is already found before!");
							    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
							    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
							    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
							    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, false)) {
							    					/*
							    					 * Set related leg flags!
							    					 */
							    					pairingFound |= (1 << 2);
							    				}
							    			}
							    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
							    		}
							    	} else
				    					/*
				    					 * HB arr only.
				    					 */
							    		if ((!l.hasHbArrDutyPair(this.hbNdx))
							    				&& d.isNonHbDep(this.hbNdx)
							    				&& d.isHbArr(this.hbNdx)) {
							    			if ((pairingFound & (1 << 3)) > 0)
							    				logger.error("Pairing is already found before!");
								    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
								    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
								    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
								    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true)) {
								    					/*
								    					 * Set related leg flags!
								    					 */
								    					pairingFound |= (1 << 3);
								    				}
								    			}
								    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
								    		}						    			
							    		}
	    				}

//if ((l.getNdx() == 1017) && ((pairingFound & (1 << 2)) > 0))
//System.out.println(pairingFound);

            			if (pairingFound == 15)
            				break;
            		}
    			}
        		logger.info(l + " - " + l.hasHbDepArrDutyPair(this.hbNdx) + " " + l.hasHbDepDutyPair(this.hbNdx) + " " + l.hasNonHbDutyPair(this.hbNdx) + " " + l.hasHbArrDutyPair(this.hbNdx));
	    	}
		}

		return true;
	}

	private boolean examinePairFW(Pair p, Duty fd, Duty ld, LegView fl, LegView ll, boolean hbDep, boolean hbArr) {

    	boolean pairingFound = false;

		Duty[] nextDuties = null;

		int lastAirportNdx = ll.getArrAirport().getNdx();

		int hourCounter = 0;
		LocalDateTime nextBriefTime = ld.getNextBriefTime(this.hbNdx);

		while (true) {
			nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(lastAirportNdx, nextBriefTime);
			if ((nextDuties != null)
					&& (nextDuties.length > 0)) {
				for (Duty nd: nextDuties) {
					if (nd.isValid(this.hbNdx)) {
						if (this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, ld, nd)) {
							if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, nd, true)) {
								pairRuleContext.getAggregatorProxy().appendFw(p, nd);

								if (hbDep) {
				    				if (nd.isHbArr(this.hbNdx)) {
				    					if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
					    					pairingFound = true;
					    					this.setHasPairingFlag(p);
					    				}
					    			} else
					    				if (nd.isNonHbArr(this.hbNdx)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairFW(p, fd, nd, fl, nd.getLastLeg(), true, false))
					    						pairingFound = true;
					    				}
								} else {
				    				if (nd.isHbArr(this.hbNdx)) {
				    					if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    						if (this.examinePairBW(p, fd, nd, fl, nd.getLastLeg(), false, true))
				    							pairingFound = true;
					    				}
					    			} else
					    				if (nd.isNonHbArr(this.hbNdx)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairFW(p, fd, nd, fl, nd.getLastLeg(), false, false))
					    						pairingFound = true;
					    				}									
								}

								pairRuleContext.getAggregatorProxy().removeLast(p);

								if (pairingFound)
									break;
							}
						}
					}
				}
			}
			if (pairingFound)
				break;
			hourCounter++;
			nextBriefTime = ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
			if (hourCounter > this.maxIdleTimeInAPairInHours)
				break;
			if (ChronoUnit.HOURS.between(fd.getBriefTime(this.hbNdx), ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter)) > this.maxPairingLengthInHours)
				break;
		}

		return pairingFound;
	}

	private boolean examinePairBW(Pair p, Duty fd, Duty ld, LegView fl, LegView ll, boolean hbDep, boolean hbArr) {

    	boolean pairingFound = false;

		Duty[] prevDuties = null;

		int firstAirportNdx = fl.getDepAirport().getNdx();

		int hourCounter = 0;
		LocalDateTime prevNextBriefTime = fd.getBriefTime(this.hbNdx);

		while (true) {
			prevDuties = this.dutyIndexByArrAirportNdxNextBrieftime.getArray(firstAirportNdx, prevNextBriefTime);
			if ((prevDuties != null)
					&& (prevDuties.length > 0)) {
				for (Duty pd: prevDuties) {
					if (pd.isValid(this.hbNdx)) {
						if (this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, fd)) {
							if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, pd, false)) {
								pairRuleContext.getAggregatorProxy().appendBw(p, pd);

								if (hbArr) {
				    				if (pd.isHbDep(this.hbNdx)) {
				    					if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
					    					pairingFound = true;
					    					this.setHasPairingFlag(p);
					    				}
					    			} else
					    				if (pd.isNonHbDep(this.hbNdx)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairBW(p, pd, ld, pd.getFirstLeg(), ll, false, true))
					    						pairingFound = true;
					    				}
								} else {
				    				if (pd.isHbDep(this.hbNdx)) {
				    					if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    						if (this.examinePairFW(p, pd, ld, pd.getFirstLeg(), ll, true, false))
				    							pairingFound = true;
					    				}
					    			} else
					    				if (pd.isNonHbDep(this.hbNdx)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairBW(p, pd, ld, pd.getFirstLeg(), ll, false, false))
					    						pairingFound = true;
					    				}
								}

								pairRuleContext.getAggregatorProxy().removeFirst(p);

								if (pairingFound)
									break;
							}
						}
					}
				}
			}
			if (pairingFound)
				break;
			hourCounter++;
			prevNextBriefTime = fd.getBriefTime(this.hbNdx).minusHours(hourCounter);
			if (hourCounter > this.maxIdleTimeInAPairInHours)
				break;
			if (ChronoUnit.HOURS.between(fd.getBriefTime(this.hbNdx), ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter)) > this.maxPairingLengthInHours)
				break;
		}

		return pairingFound;
	}

}
