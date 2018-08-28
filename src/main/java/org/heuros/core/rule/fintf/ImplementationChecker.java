package org.heuros.core.rule.fintf;

/**
 * Functional interface to check whether a rule implementation implements a particular rule interface.
 * 
 * @author bahadrzeren
 *
 * @param <C> Type of the rule implementation instance to be checked.
 * @param <I> Type of the rule interface to be searched in rule interfaces that C implements.
 */
@FunctionalInterface
public interface ImplementationChecker<C, I> {
	public boolean isImplemented(C ruleImpl, I intfClass, Class<?>[] genericTypes);
}
