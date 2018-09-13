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

	private int numOfBases = 0;

	public PairFactory(int numOfBases) {
		this.numOfBases = numOfBases;
	}

	@Override
	public Pair generateModel() {
		Pair p = new Pair();
		p.setDuties(new ArrayList<DutyView>());
		p.setPairHbSpecs(new PairHbSpec[this.numOfBases]);
		for (int i = 0; i < this.numOfBases; i++)
			p.getPairHbSpecs()[i] = new PairHbSpec();
		return p;
	}

}
