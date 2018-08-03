package org.heuros.core.rule.inf;

public interface ExtensibilityChecker<M, C> {
	public boolean isExtensible(M model, C child);
}
