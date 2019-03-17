package org.heuros.data.model;

import java.time.LocalDateTime;
import org.heuros.core.data.base.AbstractModel;

/**
 * Model class for Leg instances.
 * 
 * @author bahadrzeren
 *
 */
public class Leg extends AbstractModel implements LegView {

	private String carrier = null;
	private int flightNo = 0;
	private String suffix = null;
	private String dep = null;
	private String arr = null;
	private AirportView depAirport = null;
	private AirportView arrAirport = null;
	private String acType = null;
	private String serviceType = null;
	private LocalDateTime sobt = null;
	private LocalDateTime sibt = null;
	private int depOffset = 0;
	private int arrOffset = 0;
	private int acSequence = 0;
	private boolean needsCockpitCrew = true;
	private boolean needsCabinCrew = true;

	private boolean inFleet = false;
	private boolean deadheadable = true;
	private boolean specialFlight = false;

	/*
	 * TODO Change this name!
	 */
	private boolean cover = false;

	private int blockTimeInMins = 0;

	private LegHbSpec[] legHbSpecs = null;

	/*
	 * Initial values of totalizers. 
	 */
	private int numOfIncludingDuties = 0;
	private int numOfIncludingDutiesWoDh = 0;
	private int numOfIncludingEffectiveDuties = 0;
	private int numOfIncludingEffectiveDutiesWoDh = 0;

	private int numOfIncludingPairs = 0;
	private int numOfIncludingPairsWoDh = 0;
	private int numOfIncludingEffectivePairs = 0;
	private int numOfIncludingEffectivePairsWoDh = 0;

	private Leg() {
	}

	@Override
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Override
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}

	@Override
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}

	@Override
	public AirportView getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(AirportView depAirport) {
		this.depAirport = depAirport;
	}

	@Override
	public AirportView getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(AirportView arrAirport) {
		this.arrAirport = arrAirport;
	}

	@Override
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}

	@Override
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Override
	public LocalDateTime getSobt() {
		return sobt;
	}
	public void setSobt(LocalDateTime sobt) {
		this.sobt = sobt;
	}

	@Override
	public LocalDateTime getSibt() {
		return sibt;
	}
	public void setSibt(LocalDateTime sibt) {
		this.sibt = sibt;
	}

	@Override
	public int getDepOffset() {
		return depOffset;
	}
	public void setDepOffset(int depOffset) {
		this.depOffset = depOffset;
	}

	@Override
	public int getArrOffset() {
		return arrOffset;
	}
	public void setArrOffset(int arrOffset) {
		this.arrOffset = arrOffset;
	}

	@Override
	public int getAcSequence() {
		return acSequence;
	}
	public void setAcSequence(int acSequence) {
		this.acSequence = acSequence;
	}

	@Override
	public boolean isNeedsCockpitCrew() {
		return needsCockpitCrew;
	}
	public void setNeedsCockpitCrew(boolean needsCockpitCrew) {
		this.needsCockpitCrew = needsCockpitCrew;
	}

	@Override
	public boolean isNeedsCabinCrew() {
		return needsCabinCrew;
	}
	public void setNeedsCabinCrew(boolean needsCabinCrew) {
		this.needsCabinCrew = needsCabinCrew;
	}

	@Override
	public boolean isAnyHbDep() {
		return this.depAirport.isAnyHb();
	}
	@Override
	public boolean isAnyHbArr() {
		return this.arrAirport.isAnyHb();
	}
	@Override
	public boolean isAnyNonHbDep() {
		return this.depAirport.isAnyNonHb();
	}
	@Override
	public boolean isAnyNonHbArr() {
		return this.arrAirport.isAnyNonHb();
	}
	@Override
	public boolean isHbDep(int hbNdx) {
		return this.depAirport.isHb(hbNdx);
	}
	@Override
	public boolean isHbArr(int hbNdx) {
		return this.arrAirport.isHb(hbNdx);
	}
	@Override
	public boolean isNonHbDep(int hbNdx) {
		return this.depAirport.isNonHb(hbNdx);
	}
	@Override
	public boolean isNonHbArr(int hbNdx) {
		return this.arrAirport.isNonHb(hbNdx);
	}

	@Override
	public boolean isInFleet() {
		return inFleet;
	}
	public void setInFleet(boolean inFleet) {
		this.inFleet = inFleet;
	}



	public int getNumOfIncludingDuties() {
		return numOfIncludingDuties;
	}
	public void setNumOfIncludingDuties(int numOfIncludingDuties) {
		this.numOfIncludingDuties = numOfIncludingDuties;
	}
	public void incNumOfIncludingDuties() {
		this.numOfIncludingDuties++;
	}

	public int getNumOfIncludingDutiesWoDh() {
		return numOfIncludingDutiesWoDh;
	}
	public void setNumOfIncludingDutiesWoDh(int numOfDutiesWoDh) {
		this.numOfIncludingDutiesWoDh = numOfDutiesWoDh;
	}
	public void incNumOfIncludingDutiesWoDh() {
		this.numOfIncludingDutiesWoDh++;
	}

	public int getNumOfIncludingEffectiveDuties() {
		return numOfIncludingEffectiveDuties;
	}
	public void setNumOfIncludingEffectiveDuties(int numOfIncludingEffectiveDuties) {
		this.numOfIncludingEffectiveDuties = numOfIncludingEffectiveDuties;
	}
	public void incNumOfIncludingEffectiveDuties() {
		this.numOfIncludingEffectiveDuties++;
	}

	public int getNumOfIncludingEffectiveDutiesWoDh() {
		return numOfIncludingEffectiveDutiesWoDh;
	}
	public void setNumOfIncludingEffectiveDutiesWoDh(int numOfIncludingEffectiveDutiesWoDh) {
		this.numOfIncludingEffectiveDutiesWoDh = numOfIncludingEffectiveDutiesWoDh;
	}
	public void incNumOfIncludingEffectiveDutiesWoDh() {
		this.numOfIncludingEffectiveDutiesWoDh++;
	}



	public int getNumOfIncludingPairs() {
		return numOfIncludingPairs;
	}
	public void setNumOfIncludingPairs(int numOfIncludingPairs) {
		this.numOfIncludingPairs = numOfIncludingPairs;
	}
	public void incNumOfIncludingPairs() {
		this.numOfIncludingPairs++;
	}

	public int getNumOfIncludingPairsWoDh() {
		return numOfIncludingPairsWoDh;
	}
	public void setNumOfIncludingPairsWoDh(int numOfIncludingPairsWoDh) {
		this.numOfIncludingPairsWoDh = numOfIncludingPairsWoDh;
	}
	public void incNumOfIncludingPairsWoDh() {
		this.numOfIncludingPairsWoDh++;
	}
	public void decNumOfIncludingPairsWoDh() {
		this.numOfIncludingPairsWoDh--;
	}

	public int getNumOfIncludingEffectivePairs() {
		return numOfIncludingEffectivePairs;
	}
	public void setNumOfIncludingEffectivePairs(int numOfIncludingEffectivePairs) {
		this.numOfIncludingEffectivePairs = numOfIncludingEffectivePairs;
	}
	public void incNumOfIncludingEffectivePairs() {
		this.numOfIncludingEffectivePairs++;
	}
	public void decNumOfIncludingEffectivePairs() {
		this.numOfIncludingEffectivePairs--;
	}

	public int getNumOfIncludingEffectivePairsWoDh() {
		return numOfIncludingEffectivePairsWoDh;
	}
	public void setNumOfIncludingEffectivePairsWoDh(int numOfIncludingEffectivePairsWoDh) {
		this.numOfIncludingEffectivePairsWoDh = numOfIncludingEffectivePairsWoDh;
	}
	public void incNumOfIncludingEffectivePairsWoDh() {
		this.numOfIncludingEffectivePairsWoDh++;
	}
	public void decNumOfIncludingEffectivePairsWoDh() {
		this.numOfIncludingEffectivePairsWoDh--;
	}



//	@Override
//	public int getNumOfIncludingHbDepDuties(int hbNdx) {
//		return this.legHbSpecs[hbNdx].getNumOfIncludingHbDepDuties();
//	}
//	public void setNumOfIncludingHbDepDuties(int hbNdx, int numOfIncludingHbDepDuties) {
//		this.legHbSpecs[hbNdx].setNumOfIncludingHbDepDuties(numOfIncludingHbDepDuties);
//	}
//	public void incNumOfIncludingHbDepDuties(int hbNdx) {
//		this.legHbSpecs[hbNdx].incNumOfIncludingHbDepDuties();
//	}
//
//	@Override
//	public int getNumOfIncludingHbArrDuties(int hbNdx) {
//		return this.legHbSpecs[hbNdx].getNumOfIncludingHbArrDuties();
//	}
//	public void setNumOfIncludingHbArrDuties(int hbNdx, int numOfIncludingHbArrDuties) {
//		this.legHbSpecs[hbNdx].setNumOfIncludingHbArrDuties(numOfIncludingHbArrDuties);
//	}
//	public void incNumOfIncludingHbArrDuties(int hbNdx) {
//		this.legHbSpecs[hbNdx].incNumOfIncludingHbArrDuties();
//	}
//
//	@Override
//	public int getNumOfIncludingNonHbDepDuties(int hbNdx) {
//		return this.legHbSpecs[hbNdx].getNumOfIncludingNonHbDepDuties();
//	}
//	public void setNumOfIncludingNonHbDepDuties(int hbNdx, int numOfIncludingNonHbDepDuties) {
//		this.legHbSpecs[hbNdx].setNumOfIncludingNonHbDepDuties(numOfIncludingNonHbDepDuties);
//	}
//	public void incNumOfIncludingNonHbDepDuties(int hbNdx) {
//		this.legHbSpecs[hbNdx].incNumOfIncludingNonHbDepDuties();
//	}
//
//	@Override
//	public int getNumOfIncludingNonHbArrDuties(int hbNdx) {
//		return this.legHbSpecs[hbNdx].getNumOfIncludingNonHbArrDuties();
//	}
//	public void setNumOfIncludingNonHbArrDuties(int hbNdx, int numOfIncludingNonHbArrDuties) {
//		this.legHbSpecs[hbNdx].setNumOfIncludingNonHbArrDuties(numOfIncludingNonHbArrDuties);
//	}
//	public void incNumOfIncludingNonHbArrDuties(int hbNdx) {
//		this.legHbSpecs[hbNdx].incNumOfIncludingNonHbArrDuties();
//	}



	@Override
	public boolean isDeadheadable() {
		return deadheadable;
	}
	public void setDeadheadable(boolean deadheadable) {
		this.deadheadable = deadheadable;
	}

	@Override
	public boolean isSpecialFlight() {
		return specialFlight;
	}
	public void setSpecialFlight(boolean specialFlight) {
		this.specialFlight = specialFlight;
	}

//	public boolean isCritical() {
//		return critical;
//	}
//	public void setCritical(boolean critical) {
//		this.critical = critical;
//	}
//	public boolean isCriticalWoDh() {
//		return criticalWoDh;
//	}
//	public void setCriticalWoDh(boolean criticalWoDh) {
//		this.criticalWoDh = criticalWoDh;
//	}



	@Override
	public boolean isCover() {
		return cover;
	}
	public void setCover(boolean cover) {
		this.cover = cover;
	}

	@Override
	public int getBlockTimeInMins() {
		return blockTimeInMins;
	}
	public void setBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins = blockTimeInMins;
	}


	public void setHasPair(int hbNdx, boolean value) {
		this.legHbSpecs[hbNdx].setHasPair(value);
	}
	public void setHasHbDepArrDutyPair(int hbNdx, boolean value) {
		this.legHbSpecs[hbNdx].setHasHbDepArrDutyPair(value);
	}
	public void setHasHbDepDutyPair(int hbNdx, boolean value) {
		this.legHbSpecs[hbNdx].setHasHbDepDutyPair(value);
	}
	public void setHasNonHbDutyPair(int hbNdx, boolean value) {
		this.legHbSpecs[hbNdx].setHasNonHbDutyPair(value);
	}
	public void setHasHbArrDutyPair(int hbNdx, boolean value) {
		this.legHbSpecs[hbNdx].setHasHbArrDutyPair(value);
	}

	@Override
	public boolean hasPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasPair();
	}
	@Override
	public boolean hasHbDepArrDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbDepArrDutyPair();
	}
	@Override
	public boolean hasHbDepDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbDepDutyPair();
	}
	@Override
	public boolean hasNonHbDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasNonHbDutyPair();
	}
	@Override
	public boolean hasHbArrDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbArrDutyPair();
	}

	@Override
	public boolean hasAcChangeWith(LegView nl) {
		return (!getAcType().equals(nl.getAcType())) || (getAcSequence() != nl.getAcSequence());
	}

	@Override
	public String toString() {
		return new StringBuilder(String.valueOf(ndx))
					.append(",").append(carrier)
					.append(",").append(flightNo)
					.append(",").append(suffix)
					.append(",").append(dep)
					.append(",").append(arr)
					.append(",").append(acType)
					.append(",").append(acSequence)
					.append(",").append(sobt)
					.append(",").append(sibt)
					.append(",").append(depOffset)
					.append(",").append(arrOffset)
					.append(",").append(serviceType)
					.append(",").append(needsCockpitCrew)
					.append(",").append(needsCabinCrew)
					.append(", ZeroDh/Total#D:").append(numOfIncludingDutiesWoDh).append("/").append(numOfIncludingDuties)
					.toString();
	}

	public static Leg newInstance(int numOfBases) {
		Leg l = new Leg();
		l.legHbSpecs = new LegHbSpec[numOfBases];
		for (int i = 0; i < numOfBases; i++)
			l.legHbSpecs[i] = new LegHbSpec();
		return l;
	}
}
