package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface StarterChecker<P extends View, C extends View> {
	public boolean canBeStarter(C model);
}
