package org.heuros.core.rule.inf;

public interface Introducer<M> extends Rule {
	public boolean introduce(M m);
}
