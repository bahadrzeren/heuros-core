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

	private double score = 0.0;

	/*
	 * TODO Change this name as numOfIncludingDuties.
	 */
	private int numOfDutiesIncludes = 0;
	private int numOfDutiesIncludesHbDep = 0;
	private int numOfDutiesIncludesHbArr = 0;
	private int numOfDutiesIncludesNonHbDep = 0;
	private int numOfDutiesIncludesNonHbArr = 0;

	private boolean inFleet = false;
	private boolean deadheadable = true;
	private boolean specialFlight = false;

	/*
	 * TODO Change this name!
	 */
	private boolean cover = false;

	private int blockTimeInMins = 0;

	private LegHbSpec[] legHbSpecs = null;

	private Leg() {
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public AirportView getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(AirportView depAirport) {
		this.depAirport = depAirport;
	}

	public AirportView getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(AirportView arrAirport) {
		this.arrAirport = arrAirport;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDateTime getSobt() {
		return sobt;
	}

	public void setSobt(LocalDateTime sobt) {
		this.sobt = sobt;
	}

	public LocalDateTime getSibt() {
		return sibt;
	}

	public void setSibt(LocalDateTime sibt) {
		this.sibt = sibt;
	}

	public int getDepOffset() {
		return depOffset;
	}

	public void setDepOffset(int depOffset) {
		this.depOffset = depOffset;
	}

	public int getArrOffset() {
		return arrOffset;
	}

	public void setArrOffset(int arrOffset) {
		this.arrOffset = arrOffset;
	}

	public int getAcSequence() {
		return acSequence;
	}

	public void setAcSequence(int acSequence) {
		this.acSequence = acSequence;
	}

	public boolean isNeedsCockpitCrew() {
		return needsCockpitCrew;
	}

	public void setNeedsCockpitCrew(boolean needsCockpitCrew) {
		this.needsCockpitCrew = needsCockpitCrew;
	}

	public boolean isNeedsCabinCrew() {
		return needsCabinCrew;
	}

	public void setNeedsCabinCrew(boolean needsCabinCrew) {
		this.needsCabinCrew = needsCabinCrew;
	}

	public boolean isAnyHbDep() {
		return this.depAirport.isAnyHb();
	}
	public boolean isAnyHbArr() {
		return this.arrAirport.isAnyHb();
	}
	public boolean isAnyNonHbDep() {
		return this.depAirport.isAnyNonHb();
	}
	public boolean isAnyNonHbArr() {
		return this.arrAirport.isAnyNonHb();
	}
	public boolean isHbDep(int hbNdx) {
		return this.depAirport.isHb(hbNdx);
	}
	public boolean isHbArr(int hbNdx) {
		return this.arrAirport.isHb(hbNdx);
	}
	public boolean isNonHbDep(int hbNdx) {
		return this.depAirport.isNonHb(hbNdx);
	}
	public boolean isNonHbArr(int hbNdx) {
		return this.arrAirport.isNonHb(hbNdx);
	}


	public boolean isInFleet() {
		return inFleet;
	}

	public void setInFleet(boolean inFleet) {
		this.inFleet = inFleet;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getNumOfDutiesIncludes() {
		return numOfDutiesIncludes;
	}

	public void setNumOfDutiesIncludes(int numOfDutiesIncludes) {
		this.numOfDutiesIncludes = numOfDutiesIncludes;
	}

	public void incNumOfDutiesIncludes() {
		this.numOfDutiesIncludes++;
	}

	public int getNumOfDutiesIncludesHbDep() {
		return numOfDutiesIncludesHbDep;
	}

	public void setNumOfDutiesIncludesHbDep(int numOfDutiesIncludesHbDep) {
		this.numOfDutiesIncludesHbDep = numOfDutiesIncludesHbDep;
	}

	public void incNumOfDutiesIncludesHbDep() {
		this.numOfDutiesIncludesHbDep++;
	}

	public int getNumOfDutiesIncludesHbArr() {
		return numOfDutiesIncludesHbArr;
	}

	public void setNumOfDutiesIncludesHbArr(int numOfDutiesIncludesHbArr) {
		this.numOfDutiesIncludesHbArr = numOfDutiesIncludesHbArr;
	}

	public void incNumOfDutiesIncludesHbArr() {
		this.numOfDutiesIncludesHbArr++;
	}

	public int getNumOfDutiesIncludesNonHbDep() {
		return numOfDutiesIncludesNonHbDep;
	}

	public void setNumOfDutiesIncludesNonHbDep(int numOfDutiesIncludesNonHbDep) {
		this.numOfDutiesIncludesNonHbDep = numOfDutiesIncludesNonHbDep;
	}

	public void incNumOfDutiesIncludesNonHbDep() {
		this.numOfDutiesIncludesNonHbDep++;
	}

	public int getNumOfDutiesIncludesNonHbArr() {
		return numOfDutiesIncludesNonHbArr;
	}

	public void setNumOfDutiesIncludesNonHbArr(int numOfDutiesIncludesNonHbArr) {
		this.numOfDutiesIncludesNonHbArr = numOfDutiesIncludesNonHbArr;
	}

	public void incNumOfDutiesIncludesNonHbArr() {
		this.numOfDutiesIncludesNonHbArr++;
	}

	public boolean isDeadheadable() {
		return deadheadable;
	}

	public void setDeadheadable(boolean deadheadable) {
		this.deadheadable = deadheadable;
	}

	public boolean isSpecialFlight() {
		return specialFlight;
	}

	public void setSpecialFlight(boolean specialFlight) {
		this.specialFlight = specialFlight;
	}

	public boolean isCover() {
		return cover;
	}

	public void setCover(boolean cover) {
		this.cover = cover;
	}

	public int getBlockTimeInMins() {
		return blockTimeInMins;
	}

	public void setBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins = blockTimeInMins;
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

	public boolean hasHbDepArrDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbDepArrDutyPair();
	}
	public boolean hasHbDepDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbDepDutyPair();
	}
	public boolean hasNonHbDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasNonHbDutyPair();
	}
	public boolean hasHbArrDutyPair(int hbNdx) {
		return this.legHbSpecs[hbNdx].isHasHbArrDutyPair();
	}

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
