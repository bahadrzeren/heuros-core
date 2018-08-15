package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface ExtensibilityChecker<M extends View, C extends View> {
	public boolean isExtensible(M parentModel, C childModel);
}
