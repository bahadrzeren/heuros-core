package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ExtensibilityCheckerProxy<M extends View>
								implements ExtensibilityChecker<M> {

	private RuleRepository<ExtensibilityChecker<M>> repo;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<M>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isExtensible(M m) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(m))
				return false;
		return true;
	}
}
