package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;

public interface Validator<M extends Model> {
	public ValidationStatus isValid(M m);
}
