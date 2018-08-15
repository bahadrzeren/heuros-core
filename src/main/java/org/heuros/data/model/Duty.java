package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.Model;

public class Duty implements Model, DutyView {

	private List<DutyLeg> dutyLegs;

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}
}
