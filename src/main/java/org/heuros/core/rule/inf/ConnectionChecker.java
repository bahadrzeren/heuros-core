package org.heuros.core.rule.inf;

import org.heuros.core.data.base.View;

public interface ConnectionChecker<M extends View> {
	public boolean areConnectable(M prevModel, 
									M nextModel);
}
