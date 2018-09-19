package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.FinalChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for TotalizerChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that will be validated.
 * @see FinalChecker
 */
public class FinalCheckerProxy<V extends View> implements FinalCheckerPro<V> {

	private RuleRepository<FinalChecker<V>> repo = null;

	private int numOfBases = 0;

	public FinalCheckerProxy(RuleRepository<FinalChecker<V>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean acceptable(int hbNdx, V m) {
		for (int i = 0; i < this.repo.getRules().size(); i++) {
			if (!this.repo.getRules().get(i).acceptable(hbNdx, m))
				return false;
		}
		return true;
	}

	@Override
	public int acceptable(V m) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.acceptable(i, m))
				res |= (1 << i);
		return res;
	}
}
