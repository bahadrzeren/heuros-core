package org.heuros.data.repo;

import java.util.Optional;

import org.heuros.core.entity.context.repo.AbstractDataRepository;
import org.heuros.data.model.Airport;

public class AirportRepository extends AbstractDataRepository<Airport> {
	public Airport getAirport(String airportCode) {
		Optional<Airport> optional = this.list.stream().filter((ap) -> ap.getCode().equals(airportCode)).findFirst();
		if (optional.isPresent())
			return optional.get();
		return null;
	}
}
