package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.AppendabilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
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
												, AppendabilityChecker<DutyView, LegView>
												, Validator<DutyView> {

	@Override
	public boolean areConnectable(DutyView prevModel, DutyView nextModel) {
		return true;
	}

	@Override
	public boolean introduce(Duty m) {
		return true;
	}

	@Override
	public boolean isValid(DutyView m) {
		return true;
	}

	@Override
	public boolean isAppendable(DutyView parentModel, LegView childModel) {
		return true;
	}

}
