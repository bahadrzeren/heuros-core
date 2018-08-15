package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;
import org.heuros.core.rule.RuleContext;

public class LegFactory extends AbstractModelFactory<Leg, LegView> {

	public LegFactory(RuleContext<Leg, LegView> ruleContext) {
		super(ruleContext);
	}

	@Override
	public Leg generateModel() {
		return new Leg();
	}
}
