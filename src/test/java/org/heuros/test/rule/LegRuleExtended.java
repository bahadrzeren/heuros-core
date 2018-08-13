package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegWrapper;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegRuleExtended extends AbstractRule
									implements Introducer<LegWrapper, Leg>, ConnectionChecker<LegWrapper, Leg> {

	@Override
	public boolean areConnectable(LegWrapper prev, LegWrapper next) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(LegWrapper m) {
		// TODO Auto-generated method stub
		return true;
	}

}
