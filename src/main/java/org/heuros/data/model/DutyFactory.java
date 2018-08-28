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

	@Override
	public Duty generateModel() {
		Duty d = new Duty();
		d.setLegs(new ArrayList<LegView>());
		return d;
	}

}
