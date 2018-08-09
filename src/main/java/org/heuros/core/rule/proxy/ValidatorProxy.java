package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.repo.RuleRepository;

public class ValidatorProxy<M extends Model> implements Validator<M> {

	private RuleRepository<Validator<M>, M> repo;

	public ValidatorProxy(RuleRepository<Validator<M>, M> repo) {
		this.repo = repo;
	}

	@Override
	public ValidationStatus isValid(M t) {
		ValidationStatus res = ValidationStatus.valid;
		for (int i = 0; i < this.repo.getRules().size(); i++) {
			ValidationStatus valState = this.repo.getRules().get(i).isValid(t);
			if (valState == ValidationStatus.invalid)
				return ValidationStatus.invalid;
			else
				if (valState == ValidationStatus.extensible)
					res = ValidationStatus.extensible;
		}
		return res;
	}
}
