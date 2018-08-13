package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;

public interface Validator<M extends Model, E extends Extension> {
	public ValidationStatus isValid(M m, E e);
}
