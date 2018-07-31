package org.heuros.core.rule.inf;

public interface Introducer<M> extends Rule<M> {
	public boolean introduce(M m);
}
