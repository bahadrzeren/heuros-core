package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

public class AirportFactory extends AbstractModelFactory<Airport> {

	@Override
	public Airport generateModel() {
		return new Airport();
	}
	
}
