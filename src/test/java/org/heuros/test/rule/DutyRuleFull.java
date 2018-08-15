package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.LegView;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull extends AbstractRule
									implements Introducer<Duty>
												, ConnectionChecker<DutyView>
												, ExtensibilityChecker<DutyView, LegView>
												, Validator<DutyView> {

	@Override
	public boolean areConnectable(DutyView prevModel, DutyView nextModel) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(Duty m) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ValidationStatus isValid(DutyView m) {
		// TODO Auto-generated method stub
		return ValidationStatus.valid;
	}

	@Override
	public boolean isExtensible(DutyView parentModel, LegView childModel) {
		// TODO Auto-generated method stub
		return true;
	}

}
