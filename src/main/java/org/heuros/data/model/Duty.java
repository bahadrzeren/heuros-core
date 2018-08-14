package org.heuros.data.model;

import java.util.List;

public class Duty implements DutyModel {

	private List<DutyLeg> dutyLegs;

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}
}
