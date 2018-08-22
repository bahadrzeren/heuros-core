package org.heuros.context;

import org.heuros.core.entity.context.AbstractEntityContext;
import org.heuros.data.model.Airport;
import org.heuros.data.model.AirportFactory;
import org.heuros.data.model.AirportView;
import org.heuros.data.repo.AirportRepository;
import org.heuros.rule.AirportRuleContext;

public class AirportContext extends AbstractEntityContext<Airport> {

	public AirportView getAirport(String airportCode) {
		Airport ap = ((AirportRepository) this.dataRepository).getAirport(airportCode);
		if (ap == null) {
			ap = ((AirportFactory) this.modelFactory).generateModel(airportCode);
			if (((AirportRuleContext) this.ruleContext).getIntroducerProxy().introduce(ap))
				this.dataRepository.addToRepo(ap);
			else
				return null;
		}
		return ap;
	}
}
