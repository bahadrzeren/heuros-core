package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ExtensibilityCheckerProxy<W extends Wrapper<M>, M extends Model, R extends Wrapper<C>, C extends Model> implements ExtensibilityChecker<W, M, R, C> {

	private RuleRepository<ExtensibilityChecker<W, M, R, C>> repo;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<W, M, R, C>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isExtensible(W model, R child) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(model, child))
				return false;
		return true;
	}
}
