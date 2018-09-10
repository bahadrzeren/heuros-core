package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.TotalizerChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for TotalizerChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that will be validated.
 * @see TotalizerChecker
 */
public class TotalizerCheckerProxy<V extends View> implements TotalizerCheckerPro<V> {

	private RuleRepository<TotalizerChecker<V>> repo = null;

	private int numOfBases = 0;

	public TotalizerCheckerProxy(RuleRepository<TotalizerChecker<V>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean isValid(V m, int hbNdx) {
		for (int i = 0; i < this.repo.getRules().size(); i++) {
			if (!this.repo.getRules().get(i).isValid(m, hbNdx))
				return false;
		}
		return true;
	}

	@Override
	public int isValid(V m) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.isValid(m, i))
				res |= (1 << i);
		return res;
	}
}
