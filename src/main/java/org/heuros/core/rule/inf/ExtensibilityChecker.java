package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface ExtensibilityChecker<P extends View, C extends View> {
	public boolean isExtensible(P parentModel, C childModel);
}
