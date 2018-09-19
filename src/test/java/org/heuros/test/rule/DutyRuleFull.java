package org.heuros.test.rule;

import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.RuleImplementation;
import org.heuros.core.rule.intf.FinalChecker;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.LegView;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull implements Introducer<Duty>,
										ConnectionChecker<DutyView>, 
										AppendabilityChecker<DutyView, LegView>, 
										FinalChecker<DutyView> {

	@Override
	public boolean areConnectable(int hbNdx, DutyView prevModel, DutyView nextModel) {
		return true;
	}

	@Override
	public boolean introduce(Duty m) {
		return true;
	}

	@Override
	public boolean acceptable(int hbNdx, DutyView m) {
		return true;
	}

	@Override
	public boolean isAppendable(int hbNdx, DutyView parentModel, LegView childModel) {
		return true;
	}

}
