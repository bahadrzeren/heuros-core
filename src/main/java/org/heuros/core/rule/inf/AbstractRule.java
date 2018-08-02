package org.heuros.core.rule.inf;

public class AbstractRule implements Rule {
	@Override
	public RuleImplementation getAnnotation() {
		return this.getClass().getAnnotation(RuleImplementation.class);
	}

	@Override
	public boolean isImplemented(Class<?> ruleInterfaceType) {
		for (Class<?> intrface: this.getClass().getInterfaces()) {
			if (intrface == ruleInterfaceType)
				return true;
		}
		return false;
	}
}
