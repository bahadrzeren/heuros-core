package org.heuros.core.rule.fintf;

import org.heuros.core.rule.intf.RuleImplementation;

@FunctionalInterface
public interface RuleAnnotationGetter<C> {
	public RuleImplementation getRuleImplementation(C c);
}
