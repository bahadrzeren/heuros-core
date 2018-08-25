package org.heuros.rule;

import org.heuros.data.model.Duty;

@FunctionalInterface
public interface LazyDutyValidator {
	public boolean validateDuty(Duty d,
								LegRuleContext legRuleContext,
								DutyRuleContext dutyRuleContext);
}
