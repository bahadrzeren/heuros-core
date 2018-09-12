package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.StarterChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for StarterChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances.
 * @param <C> Type of the child model instances.
 * @see StarterChecker
 */
public class StarterCheckerProxy<P extends View, C extends View>
								implements StarterCheckerPro<P, C> {

	private RuleRepository<StarterChecker<P, C>> repo = null;

	private int numOfBases = 0;

	public StarterCheckerProxy(RuleRepository<StarterChecker<P, C>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean canBeStarter(int hbNdx, C childModel) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).canBeStarter(hbNdx, childModel))
				return false;
		return true;
	}

	@Override
	public int canBeStarter(C model) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.canBeStarter(i, model))
				res |= (1 << i);
		return res;
	}
}
