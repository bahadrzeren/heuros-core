package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

public class PairFactory extends AbstractModelFactory<Pair> {

	@Override
	public Pair generateModel() {
		return new Pair();
	}

}
