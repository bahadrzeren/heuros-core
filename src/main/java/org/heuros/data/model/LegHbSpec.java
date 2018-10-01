package org.heuros.data.model;

public class LegHbSpec {
	private int numOfDutiesIncludesHbDep = 0;
	private int numOfDutiesIncludesHbArr = 0;
	private int numOfDutiesIncludesNonHbDep = 0;
	private int numOfDutiesIncludesNonHbArr = 0;

	private boolean hasHbDepArrDutyPair = false;
	private boolean hasHbDepDutyPair = false;
	private boolean hasNonHbDutyPair = false;
	private boolean hasHbArrDutyPair = false;

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

	public boolean isHasHbDepArrDutyPair() {
		return hasHbDepArrDutyPair;
	}
	public void setHasHbDepArrDutyPair(boolean hasHbDepArrDutyPair) {
		this.hasHbDepArrDutyPair = hasHbDepArrDutyPair;
	}
	public boolean isHasHbDepDutyPair() {
		return hasHbDepDutyPair;
	}
	public void setHasHbDepDutyPair(boolean hasHbDepDutyPair) {
		this.hasHbDepDutyPair = hasHbDepDutyPair;
	}
	public boolean isHasNonHbDutyPair() {
		return hasNonHbDutyPair;
	}
	public void setHasNonHbDutyPair(boolean hasNonHbDutyPair) {
		this.hasNonHbDutyPair = hasNonHbDutyPair;
	}
	public boolean isHasHbArrDutyPair() {
		return hasHbArrDutyPair;
	}
	public void setHasHbArrDutyPair(boolean hasHbArrDutyPair) {
		this.hasHbArrDutyPair = hasHbArrDutyPair;
	}
}
