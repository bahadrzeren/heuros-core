package org.heuros.core.rule.proxy;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepo;

public class ExtensibilityCheckerProxy<M, C> extends AbstractRule implements ExtensibilityChecker<M, C> {

	private RuleRepo<ExtensibilityChecker<M, C>, M> repo;

	@Override
	public boolean isExtensible(M model, C child) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(model, child))
				return false;
		return true;
	}
}
