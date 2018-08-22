package org.heuros.util;

import org.heuros.core.rule.fintf.ImplementationChecker;
import org.heuros.core.rule.fintf.RuleAnnotationGetter;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.intf.RuleImplementation;

public class RuleUtil {

	public static ImplementationChecker<Rule, Class<?>> implChecker = (c, i) -> {
		for (Class<?> intrface: c.getClass().getInterfaces()) {
			if (intrface == i)
				return true;
		}
		return false;
	};

	public static RuleAnnotationGetter<Rule> ruleAnnotationGetter = (c) -> {
		return c.getClass().getAnnotation(RuleImplementation.class);
	};
}
