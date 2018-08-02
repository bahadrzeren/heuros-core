package org.heuros.core.rule.inf;

public interface Rule {
	public RuleImplementation getAnnotation();
	public boolean isImplemented(Class<?> ruleInterfaceType);
}
