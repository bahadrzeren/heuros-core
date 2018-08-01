package org.heuros.core.rule.proxy;

import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepo;

public class ExtensibilityCheckerProxy<M, C> implements ExtensibilityChecker<M, C> {

	private RuleRepo<ExtensibilityChecker<M, C>> repo;

	@Override
	public boolean isExtensible(M model, C child) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(model, child))
				return false;
		return true;
	}
}
