package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegIntroducer extends AbstractRule
									implements Introducer<LegModel, LegExtension> {

	@Override
	public boolean introduce(LegModel m, LegExtension e) {
		return true;
	}
}
