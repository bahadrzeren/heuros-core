package org.heuros.core.rule.inf;

public interface ConnectionChecker<T> {
	public boolean areConnectable(T prev, T next);
}
