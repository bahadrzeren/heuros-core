package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

public interface ExtensibilityChecker<M extends View> extends Rule {
	public boolean isExtensible(M model);
}
