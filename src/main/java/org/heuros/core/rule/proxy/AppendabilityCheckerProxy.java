package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for AppendabilityChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances.
 * @param <C> Type of the child model instances.
 * @see AppendabilityChecker
 */
public class AppendabilityCheckerProxy<P extends View, C extends View>
								implements AppendabilityChecker<P, C> {

	private RuleRepository<AppendabilityChecker<P, C>> repo = null;

	public AppendabilityCheckerProxy(RuleRepository<AppendabilityChecker<P, C>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isAppendable(P parentModel, C childModel, int hbNdx) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isAppendable(parentModel, childModel, hbNdx))
				return false;
		return true;
	}
}
