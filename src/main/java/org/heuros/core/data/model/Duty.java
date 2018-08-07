package org.heuros.core.data.model;

import java.util.List;

public class Duty {

	private List<DutyLeg> dutyLegs;

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}
}
