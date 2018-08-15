package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

public class DutyLegFactory extends AbstractModelFactory<DutyLeg> {

	@Override
	public DutyLeg generateModel() {
		return new DutyLeg();
	}
}
