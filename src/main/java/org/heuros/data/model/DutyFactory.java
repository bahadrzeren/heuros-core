package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

public class DutyFactory extends AbstractModelFactory<Duty> {

	@Override
	public Duty generateModel() {
		return new Duty();
	}

}
