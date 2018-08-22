package org.heuros.core.rule.intf;

import org.heuros.core.data.base.Model;

public interface Introducer<M extends Model> extends Rule {
	public boolean introduce(M m);
}
