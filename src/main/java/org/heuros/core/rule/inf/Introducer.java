package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public interface Introducer<W extends Wrapper<M>, M extends Model> {
	public boolean introduce(W w);
}
