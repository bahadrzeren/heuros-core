package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModelFactory;
import org.heuros.core.rule.RuleContext;

public class DutyFactory extends AbstractModelFactory<Duty, DutyView> {

	public DutyFactory(RuleContext<Duty, DutyView> ruleContext) {
		super(ruleContext);
	}

	@Override
	public Duty generateModel() {
		return new Duty();
	}

}
