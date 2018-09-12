package org.heuros.test.rule;

import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.RuleImplementation;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegRuleExtended implements Introducer<Leg>, 
											ConnectionChecker<LegView> {

	@Override
	public boolean areConnectable(int hbNdx, LegView prevModel, LegView nextModel) {
		return true;
	}

	@Override
	public boolean introduce(Leg m) {
		return true;
	}

}
