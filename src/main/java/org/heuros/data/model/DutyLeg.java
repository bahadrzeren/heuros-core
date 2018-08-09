package org.heuros.data.model;

public class DutyLeg {
	
	private DutyImpl duty;
	private LegImpl leg;

	public DutyImpl getDuty() {
		return duty;
	}

	public void setDuty(DutyImpl duty) {
		this.duty = duty;
	}

	public LegImpl getLeg() {
		return leg;
	}

	public void setLeg(LegImpl leg) {
		this.leg = leg;
	}
}
