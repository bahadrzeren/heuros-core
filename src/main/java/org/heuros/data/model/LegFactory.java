package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

public class LegFactory extends AbstractModelFactory<Leg> {

	@Override
	public Leg generateModel() {
		return new Leg();
	}
}
