package org.heuros.core.rule.fintf;

@FunctionalInterface
public interface ImplementationChecker<C, I> {
	public boolean isImplemented(C c, Class<?> i);
}
