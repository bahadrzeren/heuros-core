package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModel;

public class DutyLeg extends AbstractModel implements DutyLegView {
	
	private DutyView duty;
	private LegView leg;
	private boolean active;

	public DutyView getDuty() {
		return duty;
	}

	public void setDuty(DutyView duty) {
		this.duty = duty;
	}

	public LegView getLeg() {
		return leg;
	}

	public void setLeg(LegView leg) {
		this.leg = leg;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
