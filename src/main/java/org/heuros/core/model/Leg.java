package org.heuros.core.model;

import java.time.LocalDateTime;

public class Leg {

	private String carrier;
	private int fligtNo;
	private String suffix;
	private String dep;
	private String arr;
	private String acType;
	private String serviceType;
	private LocalDateTime sobt;
	private LocalDateTime sibt;
	private int depOffset;
	private int arrOffset;
	private int acSequence = 0;
	private boolean needsCockpitCrew = true;
	private boolean needsCabinCrew = true;

	public Leg(){
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public int getFligtNo() {
		return fligtNo;
	}

	public void setFligtNo(int fligtNo) {
		this.fligtNo = fligtNo;
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

	@Override
	public String toString() {
		return new StringBuilder(carrier)
					.append(",").append(fligtNo)
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
