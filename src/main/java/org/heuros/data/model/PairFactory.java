package org.heuros.data.model;

import java.util.ArrayList;

import org.heuros.core.data.base.AbstractModelFactory;

/**
 * Factory class used for creating Pair instances.
 * 
 * @author bahadrzeren
 *
 */
public class PairFactory extends AbstractModelFactory<Pair> {

	@Override
	public Pair generateModel() {
		Pair p = new Pair();
		p.setDuties(new ArrayList<DutyView>());
		return p;
	}

}
