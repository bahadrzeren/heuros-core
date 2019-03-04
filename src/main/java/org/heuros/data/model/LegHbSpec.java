package org.heuros.data.model;

public class LegHbSpec {
//	private int numOfIncludingHbDepDuties = 0;
//	private int numOfIncludingHbArrDuties = 0;
//	private int numOfIncludingNonHbDepDuties = 0;
//	private int numOfIncludingNonHbArrDuties = 0;

	private boolean hasPair = false;
	private boolean hasHbDepArrDutyPair = false;
	private boolean hasHbDepDutyPair = false;
	private boolean hasNonHbDutyPair = false;
	private boolean hasHbArrDutyPair = false;

//	public int getNumOfIncludingHbDepDuties() {
//		return numOfIncludingHbDepDuties;
//	}
//	public void setNumOfIncludingHbDepDuties(int numOfIncludingHbDepDuties) {
//		this.numOfIncludingHbDepDuties = numOfIncludingHbDepDuties;
//	}
//	public void incNumOfIncludingHbDepDuties() {
//		this.numOfIncludingHbDepDuties++;
//	}
//	public int getNumOfIncludingHbArrDuties() {
//		return numOfIncludingHbArrDuties;
//	}
//	public void setNumOfIncludingHbArrDuties(int numOfIncludingHbArrDuties) {
//		this.numOfIncludingHbArrDuties = numOfIncludingHbArrDuties;
//	}
//	public void incNumOfIncludingHbArrDuties() {
//		this.numOfIncludingHbArrDuties++;
//	}
//	public int getNumOfIncludingNonHbDepDuties() {
//		return numOfIncludingNonHbDepDuties;
//	}
//	public void setNumOfIncludingNonHbDepDuties(int numOfIncludingNonHbDepDuties) {
//		this.numOfIncludingNonHbDepDuties = numOfIncludingNonHbDepDuties;
//	}
//	public void incNumOfIncludingNonHbDepDuties() {
//		this.numOfIncludingNonHbDepDuties++;
//	}
//	public int getNumOfIncludingNonHbArrDuties() {
//		return numOfIncludingNonHbArrDuties;
//	}
//	public void setNumOfIncludingNonHbArrDuties(int numOfIncludingNonHbArrDuties) {
//		this.numOfIncludingNonHbArrDuties = numOfIncludingNonHbArrDuties;
//	}
//	public void incNumOfIncludingNonHbArrDuties() {
//		this.numOfIncludingNonHbArrDuties++;
//	}

	public boolean isHasPair() {
		return hasPair;
	}
	public void setHasPair(boolean hasPair) {
		this.hasPair = hasPair;
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
