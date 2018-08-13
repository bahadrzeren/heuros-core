package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public interface ConnectionChecker<W extends Wrapper<M>, M extends Model> {
	public boolean areConnectable(W prev, W next);
}
