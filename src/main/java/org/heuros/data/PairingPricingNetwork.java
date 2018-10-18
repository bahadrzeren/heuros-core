package org.heuros.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.OneDimUniqueIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.data.model.Pair;
import org.heuros.data.repo.DutyRepository;
import org.heuros.data.repo.LegRepository;
import org.heuros.rule.DutyRuleContext;

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
    			int dept = this.maxPairingLengthInDays;
    			if (!hbDep)
    				dept--;

    			int hourCounter = 0;
    			LocalDateTime nextBriefTime = pd.getNextBriefTime(this.hbNdx);

    			while (true) {
    				Duty[] nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(pdArrLeg.getArrAirport().getNdx(), nextBriefTime);

    				if ((nextDuties != null)
    						&& (nextDuties.length > 0)) {
    					for (Duty nd: nextDuties) {
    						if (nd.isValid(this.hbNdx)
    			    				&& nd.hasPairing(this.hbNdx)
    			    				&& nd.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)
    			    				&& this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, nd)) {
	    						boolean hbArr = nd.getLastArrAirport().isHb(this.hbNdx);
	        					if ((hbArr && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx)) <= dept))
	        							|| ((!hbArr) && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx)) <= dept - 1))) {
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

    				hourCounter++;
    				nextBriefTime = pd.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
    				if (hourCounter > this.maxIdleTimeInAPairInHours)
    					break;
    			}
    			PairingPricingNetwork.logger.info(pd.getNdx() + "th duty is processed and " + numOfConnections + "/" + totalNumOfConnections + " connections found!");
    		}
    	}
	}

	private void addToPartialNetwork(List<HashSet<Integer>> partialNet, Duty duty) {
		HashSet<Integer> list = partialNet.get(duty.getNdx());
	}

	public List<HashSet<Integer>> generatePartialNetwork(Duty[] duties) {

		List<HashSet<Integer>> partialNet = new ArrayList<HashSet<Integer>>(this.duties.size());

		for (Duty duty: duties) {

			LegView fl = duty.getFirstLeg();
			LegView ll = duty.getLastLeg();

			if (duty.isHbDep(this.hbNdx)) {
				if (duty.isHbArr(this.hbNdx)) {
					partialNet[duty.getNdx()]
				} else {
//					bestSoFar = this.fwNetworkSearch(duty, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties);
				}
			} else
				if (heuristicNo > 0) {
					if (duty.isHbArr(this.hbNdx)) {
						
					} else {
						
					}
				}
		}

	}

	private QualityMetric fwNetworkSearch(Pair p,
			QualityMetric bestSoFar,
			Duty fd, 
			Duty ld, 
			LegView fl, 
			LegView ll, 
			boolean hbDep, 
			boolean hbArr, 
			int dept,
			int[] numOfCoveringsInDuties,
			int[] blockTimeOfCoveringsInDuties) {
		LegView[] nextLegs = this.dutyNetwork.getNextBriefLegIndexByDutyNdx().getArray(duty.getNdx());
		for (LegView leg : nextLegs) {
			DutyView[] nextDuties = this.dutyNetwork.getDutyIndexByDepLegNdx().getArray(leg.getNdx());
			nextDuties.
		}
	}
}
