package org.heuros.data.model;

public class LegHbSpec {
	private boolean hasHbDepArrDutyPair = false;
	private boolean hasHbDepDutyPair = false;
	private boolean hasNonHbDutyPair = false;
	private boolean hasHbArrDutyPair = false;

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
