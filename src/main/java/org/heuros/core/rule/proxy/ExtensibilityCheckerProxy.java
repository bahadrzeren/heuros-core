package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for ExtensibilityChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that extensibility check will be applied to.
 * @see ExtensibilityChecker
 */
public class ExtensibilityCheckerProxy<M extends View>
								implements ExtensibilityCheckerPro<M> {

	private RuleRepository<ExtensibilityChecker<M>> repo = null;

	private int numOfBases = 0;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<M>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean isExtensible(int hbNdx, M m) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(hbNdx, m))
				return false;
		return true;
	}

	@Override
	public int isExtensible(M model) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.isExtensible(i, model))
				res |= (1 << i);
		return res;
	}
}
