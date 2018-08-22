package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.StarterChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class StarterCheckerProxy<P extends View, C extends View>
								implements StarterChecker<P, C> {

	private RuleRepository<StarterChecker<P, C>> repo;

	public StarterCheckerProxy(RuleRepository<StarterChecker<P, C>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean canBeStarter(C childModel) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).canBeStarter(childModel))
				return false;
		return true;
	}
}
