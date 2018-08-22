package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

public interface StarterChecker<P extends View, C extends View> extends Rule {
	public boolean canBeStarter(C model);
}
