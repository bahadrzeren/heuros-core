package org.heuros.data.processor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.data.PairingPricingNetwork;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.Pair;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class PairingGenerator {

	private static Logger logger = Logger.getLogger(PairingGenerator.class);

	/*
	 * TODO Single base assumption!!!
	 */
	private int hbNdx = 0;
	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInDays = 0;

	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;

	private TwoDimIndexIntXInt<Duty> hbDepArrDutyIndexByLegNdx = null;
//	private TwoDimIndexIntXInt<Duty> hbDepDutyIndexByLegNdx = null;
//	private TwoDimIndexIntXInt<Duty> nonHbDutyIndexByLegNdx = null;
//	private TwoDimIndexIntXInt<Duty> hbArrDutyIndexByLegNdx = null;
	private OneDimIndexInt<Duty> dutyIndexByLegNdx = null;

//	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
//	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime = null;
	private PairingPricingNetwork dutyNetwork = null;

	public PairingGenerator(int maxIdleTimeInAPairInHours, int maxPairingLengthInDays) {
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		this.maxPairingLengthInDays = maxPairingLengthInDays;
	}

	public PairingGenerator setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	public PairingGenerator setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	public PairingGenerator setHbDepArrDutyIndexByLegNdx(TwoDimIndexIntXInt<Duty> hbDepArrDutyIndexByLegNdx) {
		this.hbDepArrDutyIndexByLegNdx = hbDepArrDutyIndexByLegNdx;
		return this;
	}

	public PairingGenerator setDutyIndexByLegNdx(OneDimIndexInt<Duty> dutyIndexByLegNdx) {
		this.dutyIndexByLegNdx = dutyIndexByLegNdx;
		return this;
	}

//	public PairingGenerator setHbDepDutyIndexByLegNdx(TwoDimIndexIntXInt<Duty> hbDepDutyIndexByLegNdx) {
//		this.hbDepDutyIndexByLegNdx = hbDepDutyIndexByLegNdx;
//		return this;
//	}
//
//	public PairingGenerator setNonHbDutyIndexByLegNdx(TwoDimIndexIntXInt<Duty> nonHbDutyIndexByLegNdx) {
//		this.nonHbDutyIndexByLegNdx = nonHbDutyIndexByLegNdx;
//		return this;
//	}
//
//	public PairingGenerator setHbArrDutyIndexByLegNdx(TwoDimIndexIntXInt<Duty> hbArrDutyIndexByLegNdx) {
//		this.hbArrDutyIndexByLegNdx = hbArrDutyIndexByLegNdx;
//		return this;
//	}

//	public PairingGenerator setDutyIndexByDepAirportNdxBrieftime(
//			TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
//		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
//		return this;
//	}
//
//	public PairingGenerator setDutyIndexByArrAirportNdxNextBrieftime(
//			TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime) {
//		this.dutyIndexByArrAirportNdxNextBrieftime = dutyIndexByArrAirportNdxNextBrieftime;
//		return this;
//	}

	public PairingGenerator setPairingPricingNetwork(PairingPricingNetwork dutyNetwork) {
		this.dutyNetwork = dutyNetwork;
		return this;
	}

//	/*
//	 * HEURISTICS
//	 */
//	private boolean evaluateRules(Pair currentPair, DutyView connDuty, DutyView d, boolean fw) {
//		if (currentPair == null) {
//			return pairRuleContext.getStarterCheckerProxy().canBeStarter(hbNdx, d);
//		} else
//			if (fw) {
//				return this.evaluateFwRules(currentPair, connDuty, d);
//			} else
//				return this.evaluateBwRules(currentPair, connDuty, d);
//	}
//
//	private boolean evaluateFwRules(Pair currentPair, DutyView lastDuty, DutyView d) {
//		if (dutyRuleContext.getConnectionCheckerProxy().areConnectable(hbNdx, lastDuty, d)) {
//			if (pairRuleContext.getAppendabilityCheckerProxy().isAppendable(hbNdx, currentPair, d, true)) {
//				pairRuleContext.getAggregatorProxy().appendFw(currentPair, d);
//				if (pairRuleContext.getFinalCheckerProxy().acceptable(hbNdx, currentPair)) {
//					pairRuleContext.getAggregatorProxy().removeLast(currentPair);
//					return true;
//				}
//				pairRuleContext.getAggregatorProxy().removeLast(currentPair);
//			}
//		}
//		return false;
//	}
//
//	private boolean evaluateBwRules(Pair currentPair, DutyView firstDuty, DutyView d) {
//		if (dutyRuleContext.getConnectionCheckerProxy().areConnectable(hbNdx, d, firstDuty)) {
//			if (pairRuleContext.getAppendabilityCheckerProxy().isAppendable(hbNdx, currentPair, d, false)) {
//				pairRuleContext.getAggregatorProxy().appendBw(currentPair, d);
//				if (pairRuleContext.getFinalCheckerProxy().acceptable(hbNdx, currentPair)) {
//					pairRuleContext.getAggregatorProxy().removeFirst(currentPair);
//					return true;
//				}
//				pairRuleContext.getAggregatorProxy().removeFirst(currentPair);
//			}
//		}
//		return false;
//	}
//
//	private DutyView fetchBestDhEffectiveDuty(Pair currentPair, DutyView connDuty, 
//												DutyView bestSoFar, DutyView[] candidates, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties, 
//												boolean fw, boolean mandHbDep, boolean mandHbArr) {
//		DutyView best = bestSoFar;
//		int bestNumOfDh = Integer.MAX_VALUE;
//		int bestDhDurationInMins = Integer.MAX_VALUE;
//		double bestAvgNumOfIncludingDutiesOfTheSameLegs = Double.MAX_VALUE;
//		if (bestSoFar != null) {
//			best = bestSoFar;
//			bestNumOfDh = bestSoFar.getNumOfLegsPassive() + numOfCoveringsInDuties[bestSoFar.getNdx()];
//			bestDhDurationInMins = bestSoFar.getBlockTimeInMinsPassive() + blockTimeOfCoveringsInDuties[bestSoFar.getNdx()];
//			bestAvgNumOfIncludingDutiesOfTheSameLegs = (1.0 * bestSoFar.getTotalNumOfIncludingDutiesOfTheSameLegs()) / bestSoFar.getNumOfLegs();
//		}
//
//		for (DutyView d: candidates) {
//			if (d.hasPairing(hbNdx)
//					&& ((!mandHbDep) || (d.getFirstDepAirport().isHb(hbNdx)))
//					&& ((!mandHbArr) || (d.getLastArrAirport().isHb(hbNdx)))) {
//
//				int dNumOfDh = d.getNumOfLegsPassive() + numOfCoveringsInDuties[d.getNdx()];
//				int dDhDurationInMins = d.getBlockTimeInMinsPassive() + blockTimeOfCoveringsInDuties[d.getNdx()];
//				double dAvgNumOfIncludingDutiesOfTheSameLegs = (1.0 * d.getTotalNumOfIncludingDutiesOfTheSameLegs()) / d.getNumOfLegs();
//
//				if ((bestNumOfDh > dNumOfDh)
//						|| ((bestNumOfDh == dNumOfDh) && (bestDhDurationInMins > dDhDurationInMins))
//						|| ((bestNumOfDh == dNumOfDh) && (bestDhDurationInMins == dDhDurationInMins) && (bestAvgNumOfIncludingDutiesOfTheSameLegs > dAvgNumOfIncludingDutiesOfTheSameLegs))) {
//					if (this.evaluateRules(currentPair, connDuty, d, fw)) {
//						best = d;
//						bestNumOfDh = dNumOfDh;
//						bestDhDurationInMins = dDhDurationInMins;
//						bestAvgNumOfIncludingDutiesOfTheSameLegs = dAvgNumOfIncludingDutiesOfTheSameLegs;
//					}
//				}
//			}
//		}
//
//		return best;
//	}
//
//	private DutyView fetchBestActiveBlockTimeEffectiveDuty(Pair currentPair, DutyView connDuty,
//															DutyView bestSoFar, DutyView[] candidates, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties, 
//															boolean fw, boolean mandHbDep, boolean mandHbArr) {
//		DutyView best = bestSoFar;
//		int bestActiveBlocktimeInMins = 0;
//		double bestAvgNumOfIncludingDutiesOfTheSameLegs = Double.MAX_VALUE;
//		if (bestSoFar != null) {
//			best = bestSoFar;
//			bestActiveBlocktimeInMins = bestSoFar.getBlockTimeInMinsActive() - blockTimeOfCoveringsInDuties[bestSoFar.getNdx()];
//			bestAvgNumOfIncludingDutiesOfTheSameLegs = (1.0 * bestSoFar.getTotalNumOfIncludingDutiesOfTheSameLegs()) / bestSoFar.getNumOfLegs();
//		}
//
//		for (DutyView d: candidates) {
//			if (d.hasPairing(hbNdx)
//					&& ((!mandHbDep) || (d.getFirstDepAirport().isHb(hbNdx)))
//					&& ((!mandHbArr) || (d.getLastArrAirport().isHb(hbNdx)))) {
//				int dActiveBlocktimeInMins = d.getBlockTimeInMinsActive() - blockTimeOfCoveringsInDuties[d.getNdx()];
//				double dAvgNumOfIncludingDutiesOfTheSameLegs = (1.0 * d.getTotalNumOfIncludingDutiesOfTheSameLegs()) / d.getNumOfLegs();
//
//				if ((bestActiveBlocktimeInMins < dActiveBlocktimeInMins)
//						|| ((bestActiveBlocktimeInMins == dActiveBlocktimeInMins) && (bestAvgNumOfIncludingDutiesOfTheSameLegs > dAvgNumOfIncludingDutiesOfTheSameLegs))) {
//					if (this.evaluateRules(currentPair, connDuty, d, fw)) {
//						best = d;
//						bestActiveBlocktimeInMins = dActiveBlocktimeInMins;
//						bestAvgNumOfIncludingDutiesOfTheSameLegs = dAvgNumOfIncludingDutiesOfTheSameLegs;
//					}
//				}
//			}
//		}
//
//		return best;
//	}
//
//	/*
//	 * FW/BW search for duties.
//	 */
//	private DutyView fetchConnectionDutyFw(Pair currentPair, DutyView lastDuty, 
//											int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties, 
//											boolean fw, boolean mandHbArr) {
//
//		DutyView res = null;
//
//		int lastAirportNdx = lastDuty.getLastArrAirport().getNdx();
//
//		int hourCounter = 0;
//
//		while (true) {
//			DutyView[] nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(lastAirportNdx, lastDuty.getNextBriefTime(hbNdx).plusHours(hourCounter));
//			if ((nextDuties != null)
//					&& (nextDuties.length > 0)) {
//				if (heuristicNo == 0)
////					res = this.fetchBestLayoverEffectiveDuty(currentPair, res, nextDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, true);
//					res = this.fetchBestDhEffectiveDuty(currentPair, lastDuty, res, nextDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, false, true);
//				else
//					if (heuristicNo == 1)
//						res = this.fetchBestDhEffectiveDuty(currentPair, lastDuty, res, nextDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, false, mandHbArr);
//					else
//						if (heuristicNo == 2)
//							res = this.fetchBestActiveBlockTimeEffectiveDuty(currentPair, lastDuty, res, nextDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, false, mandHbArr);
//			}
//			hourCounter++;
//			if (hourCounter > this.maxIdleTimeInAPairInHours)
//				break;
//		}
//		return res;
//	}
//
//	private DutyView fetchConnectionDutyBw(Pair currentPair, DutyView firstDuty, 
//											int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties, 
//											boolean fw, boolean mandHbDep) {
//
//		DutyView res = null;
//
//		int firstAirportNdx = firstDuty.getFirstDepAirport().getNdx();
//
//		int hourCounter = 0;
//
//		while (true) {
//			DutyView[] prevDuties = this.dutyIndexByArrAirportNdxNextBrieftime.getArray(firstAirportNdx, firstDuty.getBriefTime(hbNdx).minusHours(hourCounter));
//			if ((prevDuties != null)
//					&& (prevDuties.length > 0)) {
//				if (heuristicNo == 0)
////					res = this.fetchBestLayoverEffectiveDuty(currentPair, res, prevDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, false);
//					res = this.fetchBestDhEffectiveDuty(currentPair, firstDuty, res, prevDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, true, false);
//				else
//					if (heuristicNo == 1)
//						res = this.fetchBestDhEffectiveDuty(currentPair, firstDuty, res, prevDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, mandHbDep, false);
//					else
//						if (heuristicNo == 2)
//							res = this.fetchBestActiveBlockTimeEffectiveDuty(currentPair, firstDuty, res, prevDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, fw, mandHbDep, false);
//			}
//			hourCounter++;
//			if (hourCounter > this.maxIdleTimeInAPairInHours)
//				break;
//		}
//		return res;
//	}
//
//	/*
//	 * Select INITIAL Duties.
//	 */
//	private DutyView fetchInitialHbDepArrDuty(Leg legToCover, int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties) {
//		DutyView[] hbDepDuties = this.hbDepArrDutyIndexByLegNdx.getArray(hbNdx, legToCover.getNdx());
//		if (heuristicNo == 0)
////			return this.fetchBestLayoverEffectiveDuty(null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true);
//			return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, true);
//		else
//			if (heuristicNo == 1)
//				return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, true);
//			else
//				if (heuristicNo == 2)
//					return this.fetchBestActiveBlockTimeEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, true);
//		return null;
//	}
//
//	private DutyView fetchInitialHbDepDuty(Leg legToCover, int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties) {
//		DutyView[] hbDepDuties = this.hbDepDutyIndexByLegNdx.getArray(hbNdx, legToCover.getNdx());
//		if (heuristicNo == 0)
////			return this.fetchBestLayoverEffectiveDuty(null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, false);
//			return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, false);
//		else
//			if (heuristicNo == 1)
//				return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, false);
//			else
//				if (heuristicNo == 2)
//					return this.fetchBestActiveBlockTimeEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, true, true, false);
//		return null;
//	}
//
//	private DutyView fetchInitialNonHbDuty(Leg legToCover, int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties) {
//		DutyView[] hbDepDuties = this.nonHbDutyIndexByLegNdx.getArray(hbNdx, legToCover.getNdx());
//		if (heuristicNo == 0)
////			return this.fetchBestLayoverEffectiveDuty(null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false);
//			return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, false);
//		else
//			if (heuristicNo == 1)
//				return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, false);
//			else
//				if (heuristicNo == 2)
//					return this.fetchBestActiveBlockTimeEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, false);
//		return null;
//	}
//
//	private DutyView fetchInitialHbArrDuty(Leg legToCover, int heuristicNo, int[] numOfCoveringsInDuties, int[] blockTimeOfCoveringsInDuties) {
//		DutyView[] hbDepDuties = this.hbArrDutyIndexByLegNdx.getArray(hbNdx, legToCover.getNdx());
//		if (heuristicNo == 0)
////			return this.fetchBestLayoverEffectiveDuty(null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, true);
//			return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, true);
//		else
//			if (heuristicNo == 1)
//				return this.fetchBestDhEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, true);
//			else
//				if (heuristicNo == 2)
//					return this.fetchBestActiveBlockTimeEffectiveDuty(null, null, null, hbDepDuties, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, false, false, true);
//		return null;
//	}
//
//	public Pair generatePairing(Leg legToCover, int heuristicNo, int maxPairingLengthInDays,
//								int[] numOfCoveringsInDuties,
//								int[] blockTimeOfCoveringsInDuties) {
//
//
//		boolean hbDepStatus = false;
//		boolean hbArrStatus = false;
//		DutyView duty = null;
//		if ((heuristicNo == 0)
//				|| (legToCover.hasHbDepArrDutyPair(hbNdx)
//						&& (!legToCover.hasHbDepDutyPair(hbNdx)))) {
//			duty = this.fetchInitialHbDepArrDuty(legToCover, heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties);
//			if (duty != null) {
//				hbDepStatus = true;
//				hbArrStatus = true;
//			}
//		}
//		if ((duty == null) && legToCover.hasHbDepDutyPair(hbNdx)) {
//			duty = this.fetchInitialHbDepDuty(legToCover, heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties);
//			if (duty != null) {
//				hbDepStatus = true;
//			}
//		}
//		if ((duty == null) && legToCover.hasNonHbDutyPair(hbNdx)) {
//			duty = this.fetchInitialNonHbDuty(legToCover, heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties);
//		}
//		if ((duty == null) && legToCover.hasHbArrDutyPair(hbNdx)) {
//			duty = this.fetchInitialHbArrDuty(legToCover, heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties);
//			if (duty != null) {
//				hbArrStatus = true;
//			}
//		}
//
//		if (duty != null) {
//			/*
//			 * TODO Single base assumption!!!
//			 */
//			Pair pair = Pair.newInstance(hbNdx);
//			while (true) {
//				if (duty != null) {
//					//	HB dep!
//					if (hbDepStatus) {
//						if (pairRuleContext.getAppendabilityCheckerProxy().isAppendable(hbNdx, pair, duty, true)) {
//							pairRuleContext.getAggregatorProxy().appendFw(pair, duty);
//							dept--;
//							if (duty.getLastArrAirport().isHb(hbNdx))
//								hbArrStatus = true;
//						} else
//							PairingGenerator.logger.error("Non appendable hbDep pairing!");
//					} else
//						if (hbArrStatus) {
//							if (pairRuleContext.getAppendabilityCheckerProxy().isAppendable(hbNdx, pair, duty, false)) {
//								pairRuleContext.getAggregatorProxy().appendBw(pair, duty);
//								dept--;
//								if (duty.getFirstDepAirport().isHb(hbNdx))
//									hbDepStatus = true;
//							} else
//								PairingGenerator.logger.error("Non appendable hbArr pairing!");
//						} else {
//							if (pairRuleContext.getAppendabilityCheckerProxy().isAppendable(hbNdx, pair, duty, false)) {
//								pairRuleContext.getAggregatorProxy().appendBw(pair, duty);
//								dept--;
//								if (duty.getFirstDepAirport().isHb(hbNdx))
//									hbDepStatus = true;
//								if (duty.getLastArrAirport().isHb(hbNdx))
//									hbArrStatus = true;
//							} else
//								PairingGenerator.logger.error("Non appendable nonHb pairing!");
//						}
//
//					for (int i = 0; i < duty.getNumOfLegs(); i++) {
//						LegView leg = duty.getLegs().get(i);
//						numOfLegCoverings[leg.getNdx()]++;
//						DutyView[] dutiesOfLeg = this.dutyIndexByLegNdx.getArray(leg.getNdx());
//						for (int j = 0; j < dutiesOfLeg.length; j++) {
//							DutyView dutyOfLeg = dutiesOfLeg[j];
//							numOfCoveringsInDuties[dutyOfLeg.getNdx()]++;
//							blockTimeOfCoveringsInDuties[dutyOfLeg.getNdx()] += leg.getBlockTimeInMins();
//						}
//					}
//
//					if (hbDepStatus && duty.getLastArrAirport().isHb(hbNdx)) {
//						if (pairRuleContext.getFinalCheckerProxy().acceptable(hbNdx, pair)) {
//							solution.add(pair);
////							PairingGenerator.logger.debug(legToCover);
////							PairingGenerator.logger.debug(pair);
//						} else
//							PairingGenerator.logger.error("Non valid pairing!");
//						break;
//					} else
//						if (hbArrStatus && duty.getFirstDepAirport().isHb(hbNdx)) {
//							if (pairRuleContext.getFinalCheckerProxy().acceptable(hbNdx, pair)) {
//								solution.add(pair);
////								PairingGenerator.logger.debug(legToCover);
////								PairingGenerator.logger.debug(pair);
//							} else
//								PairingGenerator.logger.error("Non valid pairing!");
//							break;
//						} else {
//							if (pairRuleContext.getExtensibilityCheckerProxy().isExtensible(hbNdx, pair)) {
//								heuristicNo = chromosome.getGeneValue(geneNdx);
//								geneNdx++;
//								if (hbDepStatus)
//									duty = this.fetchConnectionDutyFw(pair, pair.getLastDuty(), heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, hbDepStatus, dept < 2);
//								else
//									if (hbArrStatus)
//										duty = this.fetchConnectionDutyBw(pair, pair.getFirstDuty(), heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, hbDepStatus, dept < 2);
//									else
//										duty = this.fetchConnectionDutyBw(pair, pair.getFirstDuty(), heuristicNo, numOfCoveringsInDuties, blockTimeOfCoveringsInDuties, hbDepStatus, dept < 3);
//							} else
//								PairingGenerator.logger.error("Non extensible pairing!");
//						}
//				} else {
//					PairingGenerator.logger.error("No connection duty is found for " + legToCover);
//					for (int a = 0; a < pair.getNumOfDuties(); a++) {
//						DutyView rduty = pair.getDuties().get(a);
//						PairingGenerator.logger.error("Duty" + (a + 1));
//						PairingGenerator.logger.error(rduty);
//						for (int i = 0; i < rduty.getNumOfLegs(); i++) {
//							LegView leg = rduty.getLegs().get(i);
//							numOfLegCoverings[leg.getNdx()]--;
//							DutyView[] dutiesOfLeg = this.dutyIndexByLegNdx.getArray(leg.getNdx());
//							for (int j = 0; j < dutiesOfLeg.length; j++) {
//								DutyView dutyOfLeg = dutiesOfLeg[j];
//								numOfCoveringsInDuties[dutyOfLeg.getNdx()]--;
//								blockTimeOfCoveringsInDuties[dutyOfLeg.getNdx()] -= leg.getBlockTimeInMins();
//							}
//						}
//					}
//					uncoveredLegs++;
//					break;
//				}
//			}
//		} else {
//			PairingGenerator.logger.error("No initial duty is found for " + legToCover);
//			uncoveredLegs++;
//		}
//	}



	private Pair generatePairing(int heuristicNo, Duty[] duties) {
		if (heuristicNo == 0) {
			
		}
	}

	public Pair generatePairing(Leg legToCover, int heuristicNo, int maxPairingLengthInDays,
								int[] numOfCoveringsInDuties,
								int[] blockTimeOfCoveringsInDuties) {
		Duty[] duties = this.dutyIndexByLegNdx.getArray(legToCover.getNdx());
		if ((duties != null)
				&& (duties.length > 0)) {
			return this.generatePairing(duties);
		}
		return null;
	}
}
