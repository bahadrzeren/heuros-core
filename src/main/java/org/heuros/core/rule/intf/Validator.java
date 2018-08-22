package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

public interface Validator<M extends View> extends Rule {
	public boolean isValid(M m);
}
