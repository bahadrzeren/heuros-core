package org.heuros.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.context.PairOptimizationContext;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.OneDimUniqueIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.data.model.Duty;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.rule.DutyRuleContext;

public class DutyLegOvernightConnNetwork {

	private static Logger logger = Logger.getLogger(DutyLegOvernightConnNetwork.class);

	/*
	 * TODO Single base assumption!!!
	 */
	private int hbNdx = 0;
	private LocalDateTime dutyProcessPeriodEndExc = null;
	private int maxNetDutySearchDeptInHours = 0;
//	private int maxPairingLengthInDays = 0;

	private List<Leg> legs = null;
	private List<Duty> duties = null;

	private DutyRuleContext dutyRuleContext = null;
//	private PairRuleContext pairRuleContext = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
//	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime= null;

	private OneDimIndexInt<Duty> dutyIndexByDepLegNdx = null;
	private OneDimIndexInt<Duty> dutyIndexByArrLegNdx = null;

	/*
	 * TODO Besides connection LEG information, here we must also store the max length of the connected duties.
	 * Otherwise it is difficult to implement pairing enumerations in BiDirPairCheck and StateCalculator classes.
	 */
	private OneDimUniqueIndexInt<Leg> nextBriefLegIndexByDutyNdx = null;
	private OneDimUniqueIndexInt<Leg> prevDebriefLegIndexByDutyNdx = null;

	public DutyLegOvernightConnNetwork(LocalDateTime dutyProcessPeriodEndExc,
									int maxNetDutySearchDeptInHours,
//									int maxPairingLengthInDays,
									PairOptimizationContext pairOptimizationContext) {
		this.dutyProcessPeriodEndExc = dutyProcessPeriodEndExc;
		this.maxNetDutySearchDeptInHours = maxNetDutySearchDeptInHours;
//		this.maxPairingLengthInDays = maxPairingLengthInDays;
		this.dutyRuleContext = pairOptimizationContext.getDutyRuleContext();
//		this.pairRuleContext = pairOptimizationContext.getPairRuleContext();
		this.dutyIndexByDepAirportNdxBrieftime = pairOptimizationContext.getDutyIndexByDepAirportNdxBrieftime();
//		this.dutyIndexByArrAirportNdxNextBrieftime = pairOptimizationContext.getDutyIndexByArrAirportNdxNextBrieftime();
		this.legs = pairOptimizationContext.getLegRepository().getModels();
		this.duties = pairOptimizationContext.getDutyRepository().getModels();
	}

	public OneDimIndexInt<Duty> getDutyIndexByDepLegNdx() {
		return dutyIndexByDepLegNdx;
	}
	public OneDimIndexInt<Duty> getDutyIndexByArrLegNdx() {
		return dutyIndexByArrLegNdx;
	}
	public OneDimUniqueIndexInt<Leg> getNextBriefLegIndexByDutyNdx() {
		return nextBriefLegIndexByDutyNdx;
	}
	public OneDimUniqueIndexInt<Leg> getPrevDebriefLegIndexByDutyNdx() {
		return prevDebriefLegIndexByDutyNdx;
	}

	public void buildNetwork() {

    	logger.info("Duty network building phase is started!");

		dutyIndexByDepLegNdx = new OneDimIndexInt<Duty>(new Duty[this.legs.size()][0]);
		dutyIndexByArrLegNdx = new OneDimIndexInt<Duty>(new Duty[this.legs.size()][0]);
		nextBriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<Leg>(new Leg[this.duties.size()][0]);
		prevDebriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<Leg>(new Leg[this.duties.size()][0]);

//		int totalNumOfDutyToDutyConnections = 0;
//		int totalNumOfDutyToLegConnections = 0;

		for (int di = 0; di < this.duties.size(); di++) {
//			int numOfDutyToDutyConnections = 0;
//			int numOfDutyToLegConnections = 0;
    		Duty pd = this.duties.get(di);
    		if (pd.isValid(this.hbNdx)
    				&& pd.hasPairing(this.hbNdx)
    				&& pd.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)) {

//if (pd.getNdx() == 4863)
//System.out.println();

    			LegView pdDepLeg = pd.getFirstLeg();
    			LegView pdArrLeg = pd.getLastLeg();

				dutyIndexByDepLegNdx.add(pdDepLeg.getNdx(), pd);
    			dutyIndexByArrLegNdx.add(pdArrLeg.getNdx(), pd);

    			boolean hbDep = pdDepLeg.getDepAirport().isHb(this.hbNdx);
    			/*
    			 * According to ChronoUnit.DAYS.between calculation, from 4 days up to 5 days gives difference of 4 days as a return value!
    			 * For nonHbDep and nonHbArr duty connections it limits the combined duration of two duties with 2 days!!!!!
    			 */
//    			int dept = this.maxPairingLengthInDays - 1;
//    			if (!hbDep)
//    				dept--;

    			int hourCounter = 0;
//    			LocalDateTime nextBriefTime = pd.getNextBriefTime(this.hbNdx);
    			LocalDateTime nextBriefTime = pd.getMinNextBriefTime(this.hbNdx);

				if (pd.isNonHbArr(this.hbNdx)) {

    				while (true) {
    					Duty[] nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(pdArrLeg.getArrAirport().getNdx(), nextBriefTime);

	    				if ((nextDuties != null)
	    						&& (nextDuties.length > 0)) {
	    					for (Duty nd: nextDuties) {

//if ((pd.getNdx() == 31427)
//	&& (nd.getNdx() == 44027))
//System.out.println();

	    						if ((!nextBriefLegIndexByDutyNdx.check(pd.getNdx(), nd.getFirstLeg().getNdx()))
	    								|| (!prevDebriefLegIndexByDutyNdx.check(nd.getNdx(), pd.getLastLeg().getNdx()))) {
		    						if (nd.isValid(this.hbNdx)
		    			    				&& nd.hasPairing(this.hbNdx)
		    			    				&& nd.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)
		    			    				&& this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, nd)) {

//if ((nd.getFirstLeg().getNdx() == 5207)
//		&& (pd.getNdx() == 31427))
//System.out.println();

		    							boolean hbArr = nd.getLastArrAirport().isHb(this.hbNdx);
			    						/*
			    						 * TODO Instead of performing minus operations all the time, debriefTime could be reduced by 1 second by default. 
			    						 */
//		        						if ((hbArr && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < dept))
//		        								|| ((!hbArr) && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < dept - 1))) {
		        						if ((ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < 2)
		        								|| ((hbDep || hbArr) && (ChronoUnit.DAYS.between(pd.getBriefTime(this.hbNdx), nd.getDebriefTime(this.hbNdx).minusSeconds(1)) < 3))) {
			        						if (ChronoUnit.MINUTES.between(pd.getNextBriefTime(this.hbNdx), nd.getBriefTime(this.hbNdx)) >= 0) {
//			        							boolean added = 
			        									nextBriefLegIndexByDutyNdx.add(pd.getNdx(), nd.getFirstLeg().getNdx(), nd.getFirstLeg());
//			        							added = added || 
			        									prevDebriefLegIndexByDutyNdx.add(nd.getNdx(), pd.getLastLeg().getNdx(), pd.getLastLeg());
//			        							if (added) {
//			        								numOfDutyToLegConnections++;
//			        								totalNumOfDutyToLegConnections++;
//			        							}
//			        							numOfDutyToDutyConnections++;
//			        							totalNumOfDutyToDutyConnections++;
				        					}
			        					}
		    						}
	    						}
	    					}
	    				}

	    				hourCounter++;
//	    				nextBriefTime = pd.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
	    				nextBriefTime = pd.getMinNextBriefTime(this.hbNdx).plusHours(hourCounter);
	    				if (hourCounter > this.maxNetDutySearchDeptInHours)
	    					break;
    				}
    			}
//    			DutyLegOvernightConnNetwork.logger.info(pd.getNdx() + "th duty is processed and " + numOfDutyToDutyConnections + "/" + totalNumOfDutyToDutyConnections + " d2d, " +
//													numOfDutyToLegConnections + "/" + totalNumOfDutyToLegConnections + " d2l connections found!");
    		}
    	}

		logger.info("Duty network building phase finished!");

		for (int di = 0; di < this.duties.size(); di++) {
//			dutyIndexByDepLegNdx = new OneDimIndexInt<DutyView>(new DutyView[this.legs.size()][0]);
//			dutyIndexByArrLegNdx = new OneDimIndexInt<DutyView>(new DutyView[this.legs.size()][0]);
//			nextBriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<LegView>(new LegView[this.duties.size()][0]);
//			prevDebriefLegIndexByDutyNdx = new OneDimUniqueIndexInt<LegView>(new LegView[this.duties.size()][0]);

			LegView[] nextLegs = nextBriefLegIndexByDutyNdx.getArray(di);
			Arrays.parallelSort(nextLegs, new Comparator<LegView>() {
				@Override
				public int compare(LegView a, LegView b) {
					if (a.getNdx() < b.getNdx())
						return -1;
					else
						if (a.getNdx() > b.getNdx())
							return 1;
					return 0;
				}
			});

			LegView[] prevLegs = prevDebriefLegIndexByDutyNdx.getArray(di);
			Arrays.parallelSort(prevLegs, new Comparator<LegView>() {
				@Override
				public int compare(LegView a, LegView b) {
					if (a.getSibt().isAfter(b.getSibt()))
						return -1;
					else
						if (a.getSibt().isBefore(b.getSibt()))
							return 1;
					return 0;
				}
			});

		}

		logger.info("Duty network is ordered!");
	}
}
