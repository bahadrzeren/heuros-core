package org.heuros.test.rule;

import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.RuleImplementation;
import org.heuros.core.rule.intf.Validator;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.LegView;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull implements Introducer<Duty>,
										ConnectionChecker<DutyView>, 
										AppendabilityChecker<DutyView, LegView>, 
										Validator<DutyView> {

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
