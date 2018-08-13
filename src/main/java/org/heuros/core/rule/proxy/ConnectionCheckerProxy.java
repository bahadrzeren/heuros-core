package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ConnectionCheckerProxy<M extends Model, E extends Extension> 
				implements ConnectionChecker<M, E> {

	private RuleRepository<ConnectionChecker<M, E>> repo;

	public ConnectionCheckerProxy(RuleRepository<ConnectionChecker<M, E>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean areConnectable(M prevModel, 
									E prevExtension, 
									M nextModel, 
									E nextExtension) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prevModel, 
															prevExtension, 
															nextModel, 
															nextExtension))
				return false;
		return true;
	}
}
