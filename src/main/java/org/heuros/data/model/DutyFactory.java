package org.heuros.data.model;

import java.util.ArrayList;

import org.heuros.core.data.base.AbstractModelFactory;

/**
 * Factory class used for creating Duty instances.
 * 
 * @author bahadrzeren
 *
 */
public class DutyFactory extends AbstractModelFactory<Duty> {

	private int numOfBases = 0;

	public DutyFactory(int numOfBases) {
		this.numOfBases = numOfBases;
	}

	@Override
	public Duty generateModel() {
		Duty d = new Duty();
		d.setLegs(new ArrayList<LegView>());
		d.setDutyHbSpecs(new DutyHbSpec[this.numOfBases]);
		for (int i = 0; i < this.numOfBases; i++)
			d.getDutyHbSpecs()[i] = new DutyHbSpec();
		return d;
	}

}
