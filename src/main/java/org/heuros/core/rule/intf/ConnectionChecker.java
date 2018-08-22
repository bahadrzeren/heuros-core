package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

public interface ConnectionChecker<M extends View> extends Rule {
	public boolean areConnectable(M prevModel, 
									M nextModel);
}
