package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;

public interface ExtensibilityChecker<M extends Model, 
										E extends Extension, 
										C extends Model, 
										X extends Extension> {
	public boolean isExtensible(M parentModel, 
								E parentExtension, 
								C childModel, 
								X childExtension);
}
