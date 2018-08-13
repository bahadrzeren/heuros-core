package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyWrapper;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegWrapper;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull extends AbstractRule
									implements Introducer<DutyWrapper, Duty>
												, ConnectionChecker<DutyWrapper, Duty>
												, ExtensibilityChecker<DutyWrapper, Duty, LegWrapper, Leg>
												, Validator<DutyWrapper, Duty> {

	@Override
	public boolean areConnectable(DutyWrapper prev, DutyWrapper next) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(DutyWrapper m) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ValidationStatus isValid(DutyWrapper m) {
		// TODO Auto-generated method stub
		return ValidationStatus.valid;
	}

	@Override
	public boolean isExtensible(DutyWrapper model, LegWrapper child) {
		// TODO Auto-generated method stub
		return true;
	}

}
