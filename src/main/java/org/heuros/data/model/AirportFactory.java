package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;
import org.heuros.core.rule.RuleContext;

public class AirportFactory extends AbstractModelFactory<Airport, AirportView> {

	public AirportFactory(RuleContext<Airport, AirportView> ruleContext) {
		super(ruleContext);
	}

	@Override
	public Airport generateModel() {
		return new Airport();
	}
	
}
