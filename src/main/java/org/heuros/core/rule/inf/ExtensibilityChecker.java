package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public interface ExtensibilityChecker<W extends Wrapper<M>, M extends Model, R extends Wrapper<C>, C extends Model> {
	public boolean isExtensible(W model, R child);
}
