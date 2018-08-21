package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface AppendabilityChecker<P extends View, C extends View> {
	public boolean isAppendable(P parentModel, C childModel);
}
