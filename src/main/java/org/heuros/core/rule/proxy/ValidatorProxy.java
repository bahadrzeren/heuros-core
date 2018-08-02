package org.heuros.core.rule.proxy;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.repo.RuleRepo;

public class ValidatorProxy<M> extends AbstractRule implements Validator<M> {

	private RuleRepo<Validator<M>, M> repo;

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
