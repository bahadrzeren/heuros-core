package org.heuros.core.rule.inf;

public interface ConnectionChecker<M> extends Rule<M> {
	public boolean areConnectable(M prev, M next);
}
