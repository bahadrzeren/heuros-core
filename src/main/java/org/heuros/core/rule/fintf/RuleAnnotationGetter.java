package org.heuros.core.rule.fintf;

import org.heuros.core.rule.intf.RuleImplementation;

/**
 * Functional interface to get RuleImplementation annotation.
 * 
 * @author bahadrzeren
 *
 * @param <C> Type of the rule implementation instance.
 */
@FunctionalInterface
public interface RuleAnnotationGetter<C> {
	public RuleImplementation getRuleImplementation(C c);
}
