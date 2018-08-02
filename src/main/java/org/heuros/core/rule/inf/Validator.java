package org.heuros.core.rule.inf;

public interface Validator<M> extends Rule {
	public ValidationStatus isValid(M m);
}
