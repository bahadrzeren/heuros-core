package org.heuros.core.rule.inf;

public interface Validator<T> {
	public ValidationStatus isValid(T t);
}
