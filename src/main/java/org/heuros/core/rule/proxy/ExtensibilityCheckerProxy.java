package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ExtensibilityCheckerProxy<P extends View, C extends View>
								implements ExtensibilityChecker<P, C> {

	private RuleRepository<ExtensibilityChecker<P, C>> repo;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<P, C>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isExtensible(P parentModel, C childModel) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(parentModel, childModel))
				return false;
		return true;
	}
}
