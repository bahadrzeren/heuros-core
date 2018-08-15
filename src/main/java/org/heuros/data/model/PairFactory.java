package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;
import org.heuros.core.rule.RuleContext;

public class PairFactory extends AbstractModelFactory<Pair, PairView> {

	public PairFactory(RuleContext<Pair, PairView> ruleContext) {
		super(ruleContext);
	}

	@Override
	public Pair generateModel() {
		return new Pair();
	}

}
