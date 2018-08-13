package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;

public interface ConnectionChecker<M extends Model, E extends Extension> {
	public boolean areConnectable(M prevModel, 
									E prevExtension, 
									M nextModel, 
									E nextExtension);
}
