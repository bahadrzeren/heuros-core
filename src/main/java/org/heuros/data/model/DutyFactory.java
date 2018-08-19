package org.heuros.data.model;

import java.util.ArrayList;

import org.heuros.core.data.base.AbstractModelFactory;

public class DutyFactory extends AbstractModelFactory<Duty> {

	@Override
	public Duty generateModel() {
		Duty d = new Duty();
		d.setDutyLegs(new ArrayList<DutyLegView>());
		return d;
	}

}
