package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegRuleExtended extends AbstractRule
									implements Introducer<Leg>, 
												ConnectionChecker<LegView> {

	@Override
	public boolean areConnectable(LegView prevModel, LegView nextModel) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(Leg m) {
		// TODO Auto-generated method stub
		return true;
	}

}
