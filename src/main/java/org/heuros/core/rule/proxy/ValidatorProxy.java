package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Validator;
import org.heuros.core.rule.repo.RuleRepository;

public class ValidatorProxy<V extends View> implements Validator<V> {

	private RuleRepository<Validator<V>> repo = null;

	public ValidatorProxy(RuleRepository<Validator<V>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isValid(V m) {
		for (int i = 0; i < this.repo.getRules().size(); i++) {
			if (!this.repo.getRules().get(i).isValid(m))
				return false;
		}
		return true;
	}
}
