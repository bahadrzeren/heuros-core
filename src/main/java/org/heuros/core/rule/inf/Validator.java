package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface Validator<M extends View> {
	public boolean isValid(M m);
}
