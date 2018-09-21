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
								implements AppendabilityCheckerPro<P, C> {

	private RuleRepository<AppendabilityChecker<P, C>> repo = null;

	private int numOfBases = 0;

	public AppendabilityCheckerProxy(RuleRepository<AppendabilityChecker<P, C>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean isAppendable(int hbNdx, P parentModel, C childModel, boolean fw) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isAppendable(hbNdx, parentModel, childModel, fw))
				return false;
		return true;
	}

	@Override
	public int isAppendable(P p, C c, boolean fw) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.isAppendable(i, p, c, fw))
				res |= (1 << i);
		return res;
	}
}
