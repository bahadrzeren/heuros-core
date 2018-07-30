package org.heuros.core.rule.inf;

public interface ExtensibilityChecker<T, P> {
	public boolean isExtensible(T item, P parent);
}
