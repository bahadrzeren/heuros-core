package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Validator;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for Validator rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that will be validated.
 * @see Validator
 */
public class ValidatorProxy<V extends View> implements Validator<V> {

	private RuleRepository<Validator<V>> repo = null;

	public ValidatorProxy(RuleRepository<Validator<V>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isValid(V m, int hbNdx) {
		for (int i = 0; i < this.repo.getRules().size(); i++) {
			if (!this.repo.getRules().get(i).isValid(m, hbNdx))
				return false;
		}
		return true;
	}
}
