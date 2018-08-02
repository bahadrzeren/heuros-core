package org.heuros.core.rule.proxy;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepo;

public class ConnectionCheckerProxy<M> extends AbstractRule implements ConnectionChecker<M> {

	private RuleRepo<ConnectionChecker<M>, M> repo;

	@Override
	public boolean areConnectable(M prev, M next) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prev, next))
				return false;
		return true;
	}
}
