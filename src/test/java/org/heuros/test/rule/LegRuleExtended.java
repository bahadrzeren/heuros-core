package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class LegRuleExtended extends AbstractRule
									implements Introducer<LegModel, LegExtension>, 
												ConnectionChecker<LegModel, LegExtension> {

	@Override
	public boolean areConnectable(LegModel prevModel, 
									LegExtension prevExtension, 
									LegModel nextModel,
									LegExtension nextExtension) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(LegModel m, LegExtension e) {
		// TODO Auto-generated method stub
		return true;
	}

}
