package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;

public interface Introducer<M extends Model> {
	public boolean introduce(M m);
}
