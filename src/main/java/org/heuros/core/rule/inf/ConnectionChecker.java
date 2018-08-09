package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;

public interface ConnectionChecker<M extends Model> {
	public boolean areConnectable(M prev, M next);
}
