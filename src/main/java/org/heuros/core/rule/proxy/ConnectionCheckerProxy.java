package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ConnectionCheckerProxy<W extends Wrapper<M>, M extends Model> implements ConnectionChecker<W, M> {

	private RuleRepository<ConnectionChecker<W, M>> repo;

	public ConnectionCheckerProxy(RuleRepository<ConnectionChecker<W, M>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean areConnectable(W prev, W next) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prev, next))
				return false;
		return true;
	}
}
