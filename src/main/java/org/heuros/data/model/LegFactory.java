package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

/**
 * Factory class used for creating Leg instances.
 * 
 * @author bahadrzeren
 *
 */
public class LegFactory extends AbstractModelFactory<Leg> {

	@Override
	public Leg generateModel() {
		return new Leg();
	}
}
