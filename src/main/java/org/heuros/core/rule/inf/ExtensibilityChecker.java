package org.heuros.core.rule.inf;

public interface ExtensibilityChecker<M, C> extends Rule {
	public boolean isExtensible(M model, C child);
}
