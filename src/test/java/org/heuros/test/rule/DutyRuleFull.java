package org.heuros.test.rule;

import org.heuros.core.data.model.Duty;
import org.heuros.core.data.model.Leg;
import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull extends AbstractRule
									implements Introducer<Duty>
												, ConnectionChecker<Duty>
												, ExtensibilityChecker<Duty, Leg>
												, Validator<Duty> {

	@Override
	public boolean areConnectable(Duty prev, Duty next) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(Duty m) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ValidationStatus isValid(Duty m) {
		// TODO Auto-generated method stub
		return ValidationStatus.valid;
	}

	@Override
	public boolean isExtensible(Duty model, Leg child) {
		// TODO Auto-generated method stub
		return true;
	}

}
