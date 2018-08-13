package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImplementation;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;
import org.heuros.data.model.DutyExtension;
import org.heuros.data.model.DutyModel;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;

@RuleImplementation(ruleName = "test rule"
					, violationMessage = "test rule violated"
					, description = "test rule details")
public class DutyRuleFull extends AbstractRule
									implements Introducer<DutyModel, DutyExtension>
												, ConnectionChecker<DutyModel, DutyExtension>
												, ExtensibilityChecker<DutyModel, 
																		DutyExtension, 
																		LegModel, 
																		LegExtension>
												, Validator<DutyModel, DutyExtension> {

	@Override
	public boolean areConnectable(DutyModel prevModel,
									DutyExtension prevExtension, 
									DutyModel nextModel,
									DutyExtension nextExtension) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean introduce(DutyModel m, DutyExtension e) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ValidationStatus isValid(DutyModel m, DutyExtension e) {
		// TODO Auto-generated method stub
		return ValidationStatus.valid;
	}

	@Override
	public boolean isExtensible(DutyModel parentModel, 
								DutyExtension parentExtension,
								LegModel childModel,
								LegExtension childExtension) {
		// TODO Auto-generated method stub
		return true;
	}

}
