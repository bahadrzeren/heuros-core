package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegWrapper;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegIntroducer extends AbstractRule
									implements Introducer<LegWrapper, Leg> {

	@Override
	public boolean introduce(LegWrapper m) {
		return true;
	}
}
