package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;

public interface ExtensibilityChecker<M extends Model, C extends Model> {
	public boolean isExtensible(M model, C child);
}
