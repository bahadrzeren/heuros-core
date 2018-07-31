package org.heuros.core.rule.inf;

public interface Validator<M> extends Rule<M> {
	public ValidationStatus isValid(M m);
}
