package org.heuros.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.OneDimUniqueIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.data.repo.DutyRepository;
import org.heuros.data.repo.LegRepository;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class PairingPricingNetwork {

	private static Logger logger = Logger.getLogger(PairingPricingNetwork.class);

	/*
	 * TODO Single base assumption!!!
	 */
	private int hbNdx = 0;
	private LocalDateTime dutyProcessPeriodEndExc = null;
	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInDays = 0;

	private List<Leg> legs = null;
	private List<Duty> duties = null;

	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
//	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime= null;

	private OneDimIndexInt<DutyView> dutyIndexByDepLegNdx = null;
	private OneDimIndexInt<DutyView> dutyIndexByArrLegNdx = null;
	private OneDimUniqueIndexInt<LegView> nextBriefLegIndexByDutyNdx = null;
	private OneDimUniqueIndexInt<LegView> prevDebriefLegIndexByDutyNdx = null;

	public PairingPricingNetwork(LocalDateTime dutyProcessPeriodEndExc,
									int maxIdleTimeInAPairInHours,
									int maxPairingLengthInDays) {
		this.dutyProcessPeriodEndExc = dutyProcessPeriodEndExc;
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		this.maxPairingLengthInDays = maxPairingLengthInDays;
	}

	public PairingPricingNetwork setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}
	public PairingPricingNetwork setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}
	public PairingPricingNetwork setDutyIndexByDepAirportNdxBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
		return this;
	}
//	public PairingPricingNetwork setDutyIndexByArrAirportNdxNextBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime) {
//		this.dutyIndexByArrAirportNdxNextBrieftime = dutyIndexByArrAirportNdxNextBrieftime;
//		return this;
//	}
	public PairingPricingNetwork setLegRepository(LegRepository legRepository) {
		this.legs = legRepository.getModels();
		return this;
	}
	public PairingPricingNetwork setDutyRepository(DutyRepository dutyRepository) {
		this.duties = dutyRepository.getModels();
		return this;
	}

	public OneDimIndexInt<DutyView> getDutyIndexByDepLegNdx() {
		return dutyIndexByDepLegNdx;
	}
	public OneDimIndexInt<DutyView> getDutyIndexByArrLegNdx() {
		return dutyIndexByArrLegNdx;
	}
	public OneDimUniqueIndexInt<LegView> getNextBriefLegIndexByDutyNdx() {
		return nextBriefLegIndexByDutyNdx;
	}
	public OneDimUniqueIndexInt<LegView> getPrevDebriefLegIndexByDutyNdx() {
		return prevDebriefLegIndexByDutyNdx;
	}

	public void buildNetwork() {

		dutyIndexByDepLegNdx = new OneDimIndexInt<DutyView>(new DutyView[this.legs.size()][0]);
		dutyIndexByArrLegNdx = new OneDimIndexInt<DutyView>(new DutyView[this.legs.size()][0]);
		nextBriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<LegView>(new LegView[this.duties.size()][0]);
		prevDebriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<LegView>(new LegView[this.duties.size()][0]);

		int totalNumOfConnections = 0;

		for (int di = 0; di < this.duties.size(); di++) {
			int numOfConnections = 0;
    		Duty pd = this.duties.get(di);
    		if (pd.isValid(this.hbNdx)
    				&& pd.hasPairing(this.hbNdx)
    				&& pd.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)) {

    			LegView pdDepLeg = pd.getFirstLeg();
    			LegView pdArrLeg = pd.getLastLeg();

				dutyIndexByDepLegNdx.add(pdDepLeg.getNdx(), pd);
    			dutyIndexByArrLegNdx.add(pdArrLeg.getNdx(), pd);

    			boolean hbDep = pdDepLeg.getDepAirport().isHb(this.hbNdx);
    			/*
    			 * According to ChronoUnit.DAYS.between calculation, from 4 days up to 5 days gives difference of 4 days as a return value!
    			 * For nonHbDep and nonHbArr duty connections it limits the combined duration of two duties with 2 days!!!!!
    			 */
    			int dept = this.maxPairingLengthInDays - 1;
    			if (!hbDep)
    				dept--;

    			int hourCounter = 0;
    			LocalDateTime nextBriefTime = pd.getNextBriefTime(this.hbNdx);

    			while (true) {
    				Duty[] nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(pdArrLeg.getArrAirport().getNdx(), nextBriefTime);

    				if ((nextDuties != null)
    						&& (nextDuties.length > 0)) {
    					for (Duty nd: nextDuties) {
    						if ((!nextBriefLegIndexByDutyNdx.check(pd.getNdx(), nd.getFirstLeg().getNdx()))
    								|| (!prevDebriefLegIndexByDutyNdx.check(nd.getNdx(), pd.getLastLeg().getNdx()))) {
	    						if (nd.isValid(this.hbNdx)
	    			    				&& nd.hasPairing(this.hbNdx)
	    			    				&& nd.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)
	    			    				&& this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, nd)) {
		    						boolean hbArr = nd.getLastArrAirport().isHb(this.hbNdx);
		        					if ((hbArr && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < dept))
		        							|| ((!hbArr) && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < dept - 1))) {
			        					if (ChronoUnit.MINUTES.between(pd.getNextBriefTime(this.hbNdx), nd.getBriefTime(this.hbNdx)) >= 0) {
			        						nextBriefLegIndexByDutyNdx.add(pd.getNdx(), nd.getFirstLeg().getNdx(), nd.getFirstLeg());
			        						prevDebriefLegIndexByDutyNdx.add(nd.getNdx(), pd.getLastLeg().getNdx(), pd.getLastLeg());
			        						numOfConnections++;
			        						totalNumOfConnections++;
			        					}
		        					}
	    						}
    						}
    					}
    				}

    				hourCounter++;
    				nextBriefTime = pd.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
    				if (hourCounter > this.maxIdleTimeInAPairInHours)
    					break;
    			}
    			PairingPricingNetwork.logger.info(pd.getNdx() + "th duty is processed and " + numOfConnections + "/" + totalNumOfConnections + " connections found!");
    		}
    	}
	}

	public PartialPairingPricingNetwork generatePartialNetwork(int heuristicNo, Duty[] sourceDuties) {

		PartialPairingPricingNetwork partialNet = new PartialPairingPricingNetwork(this.duties);
		int[] maxSearchDept = new int[this.duties.size()];

		for (Duty duty: sourceDuties) {

			if (duty.isValid(this.hbNdx)) {
				if (duty.isHbDep(this.hbNdx)) {
					if (duty.isHbArr(this.hbNdx)) {
						if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, duty))
							partialNet.addSourceDuty(duty);
					} else 
						if (heuristicNo > 0) {
							if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, duty)) {
								if (this.fwNetworkSearch(partialNet, duty, maxSearchDept, this.maxPairingLengthInDays - 1)) {
									partialNet.addSourceDuty(duty);
								}
							}
						}
				} else
					if (heuristicNo > 0) {
						if (duty.isHbArr(this.hbNdx)) {
							this.bwNetworkSearch(partialNet, duty, maxSearchDept, this.maxPairingLengthInDays - 1);
						} else {
							if (this.fwNetworkSearch(partialNet, duty, maxSearchDept, this.maxPairingLengthInDays - 2))
								this.bwNetworkSearch(partialNet, duty, maxSearchDept, this.maxPairingLengthInDays - 2);
						}
					}
			}
		}

		return partialNet;
	}

	private boolean fwNetworkSearch(PartialPairingPricingNetwork partialNet, DutyView pd, int[] maxSearchDept, int dept) {
		boolean res = false;
		LegView[] nextLegs = this.nextBriefLegIndexByDutyNdx.getArray(pd.getNdx());
		for (LegView leg : nextLegs) {
			DutyView[] nextDuties = this.dutyIndexByDepLegNdx.getArray(leg.getNdx());
			for (DutyView nd: nextDuties) {
				if (nd.isValid(this.hbNdx)
						&& (maxSearchDept[nd.getNdx()] < dept)
						&& this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, nd)) {
					if (nd.isHbArr(this.hbNdx)) {
						partialNet.addDuty(pd, nd);
						res = true;
					} else
						if (dept > 1) {
//							root.isHbDep(this.hbNdx)
							if (this.fwNetworkSearch(partialNet, nd, maxSearchDept, dept - 1)) {
								res = true;
								partialNet.addDuty(pd, nd);
							}
						}
					maxSearchDept[nd.getNdx()] = dept;
				}
			}
		}
		return res;
	}

	private boolean bwNetworkSearch(PartialPairingPricingNetwork partialNet, DutyView nd, int[] maxSearchDept, int dept) {
		boolean res = false;
		LegView[] prevLegs = this.prevDebriefLegIndexByDutyNdx.getArray(nd.getNdx());
		for (LegView leg : prevLegs) {
			DutyView[] prevDuties = this.dutyIndexByArrLegNdx.getArray(leg.getNdx());
			for (DutyView pd: prevDuties) {
				if (pd.isValid(this.hbNdx)
						&& (maxSearchDept[pd.getNdx()] < dept)
						&& this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, nd)) {
					if (pd.isHbDep(this.hbNdx)) {
						if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, pd)) {
							partialNet.addDuty(pd, nd);
							partialNet.addSourceDuty(pd);
							res = true;
						}
					} else
						if (dept > 1) {
//							root.isHbArr(this.hbNdx)
							if (this.bwNetworkSearch(partialNet, pd, maxSearchDept, dept - 1)) {
								res = true;
								partialNet.addDuty(pd, nd);
							}
						}
					maxSearchDept[pd.getNdx()] = dept;
				}
			}
		}
		return res;
	}
}
