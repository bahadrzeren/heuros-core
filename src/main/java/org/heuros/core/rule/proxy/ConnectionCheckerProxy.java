package org.heuros.core.rule.proxy;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ConnectionCheckerProxy<M> implements ConnectionChecker<M> {

	private RuleRepository<ConnectionChecker<M>, M> repo;

	public ConnectionCheckerProxy(RuleRepository<ConnectionChecker<M>, M> repo) {
		this.repo = repo;
	}

	@Override
	public boolean areConnectable(M prev, M next) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prev, next))
				return false;
		return true;
	}
}
