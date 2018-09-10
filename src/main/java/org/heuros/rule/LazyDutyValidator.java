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
	/**
	 * Validates duties from scratch.
	 * 
	 * @param d Duty instance to validate.
	 * @param legRuleContext
	 * @param dutyRuleContext
	 * @return Bitwise validation vector that stores validation status for all bases.
	 */
	public int validateDuty(Duty d,
								LegRuleContext legRuleContext,
								DutyRuleContext dutyRuleContext);
}
