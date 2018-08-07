package org.heuros.test.rule;

import org.heuros.core.data.model.Leg;
import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegIntroducer extends AbstractRule
									implements Introducer<Leg> {

	@Override
	public boolean introduce(Leg m) {
		return true;
	}
}
