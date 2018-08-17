package org.heuros.data.model;

import java.time.LocalDateTime;

import org.heuros.core.data.base.AbstractModel;

public class Leg extends AbstractModel implements LegView {

	private String carrier;
	private int flightNo;
	private String suffix;
	private String dep;
	private String arr;
	private AirportView depAirport;
	private AirportView arrAirport;
	private String acType;
	private String serviceType;
	private LocalDateTime sobt;
	private LocalDateTime sibt;
	private int depOffset;
	private int arrOffset;
	private int acSequence = 0;
	private boolean needsCockpitCrew = true;
	private boolean needsCabinCrew = true;

	private boolean inFleet = false;
	private boolean inOptimizationPeriod = false;
	private boolean deadheadable = true;
	private boolean specialFlight = false;

	private boolean cover = false;

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

	public boolean isInFleet() {
		return inFleet;
	}

	public void setInFleet(boolean inFleet) {
		this.inFleet = inFleet;
	}

	public boolean isInOptimizationPeriod() {
		return inOptimizationPeriod;
	}

	public void setInOptimizationPeriod(boolean inOptimizationPeriod) {
		this.inOptimizationPeriod = inOptimizationPeriod;
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

	@Override
	public String toString() {
		return new StringBuilder(carrier)
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
}
