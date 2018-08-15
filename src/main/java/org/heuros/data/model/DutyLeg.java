package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModel;

public class DutyLeg extends AbstractModel implements DutyLegView {
	
	private Duty duty;
	private Leg leg;

	public Duty getDuty() {
		return duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}
}
