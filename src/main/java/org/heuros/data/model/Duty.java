package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Duty extends AbstractModel implements DutyView {

	private List<DutyLeg> dutyLegs;

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}
}
