package org.heuros.test.rule;

import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.RuleImplementation;
import org.heuros.data.model.Leg;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegIntroducer implements Introducer<Leg> {

	@Override
	public boolean introduce(Leg m) {
		return true;
	}
}
