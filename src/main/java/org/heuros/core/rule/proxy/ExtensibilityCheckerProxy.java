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
								implements ExtensibilityChecker<M> {

	private RuleRepository<ExtensibilityChecker<M>> repo = null;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<M>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isExtensible(M m, int hbNdx) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(m, hbNdx))
				return false;
		return true;
	}
}
