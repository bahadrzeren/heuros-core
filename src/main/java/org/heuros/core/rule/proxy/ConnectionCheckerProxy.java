package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ConnectionCheckerProxy<V extends View> implements ConnectionChecker<V> {

	private RuleRepository<ConnectionChecker<V>> repo;

	public ConnectionCheckerProxy(RuleRepository<ConnectionChecker<V>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean areConnectable(V prevModel, V nextModel) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prevModel, nextModel))
				return false;
		return true;
	}
}
