package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;

public interface Introducer<M extends Model, E extends Extension> {
	public boolean introduce(M m, E e);
}
