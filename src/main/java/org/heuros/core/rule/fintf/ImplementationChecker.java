package org.heuros.core.rule.fintf;

@FunctionalInterface
public interface ImplementationChecker<C, I> {
	public boolean isImplemented(C ruleImpl, I intfClass, Class<?>[] genericTypes);
}
