package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

public interface AppendabilityChecker<P extends View, C extends View> extends Rule {
	public boolean isAppendable(P parentModel, C childModel);
}
