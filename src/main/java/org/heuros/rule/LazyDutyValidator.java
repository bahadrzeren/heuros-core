package org.heuros.rule;

import org.heuros.data.model.Duty;

/**
 * Functional interface that validates Duty instances from the scratch any time after generation.
 * 
 * @author bahadrzeren
 *
 */
@FunctionalInterface
public interface LazyDutyValidator {
	public boolean validateDuty(Duty d,
								LegRuleContext legRuleContext,
								DutyRuleContext dutyRuleContext);
}
