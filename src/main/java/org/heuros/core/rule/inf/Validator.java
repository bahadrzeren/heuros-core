package org.heuros.core.rule.inf;

public interface Validator<M> {
	public ValidationStatus isValid(M m);
}
