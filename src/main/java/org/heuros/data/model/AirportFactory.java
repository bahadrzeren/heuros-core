package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;

/**
 * Factory class used for creating Airport instances.
 * 
 * @author bahadrzeren
 *
 */
public class AirportFactory extends AbstractModelFactory<Airport> {

	@Override
	public Airport generateModel() {
		return new Airport();
	}

	public Airport generateModel(String airportCode) {
		Airport airport = this.generateModel();
		airport.setCode(airportCode);
		return airport;
	}
}
