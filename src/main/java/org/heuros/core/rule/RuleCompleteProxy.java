package org.heuros.core.rule;

import org.heuros.core.rule.inf.ValidationStatus;

public class RuleCompleteProxy<M, C> implements RuleComplete<M, C> {

	private RuleRepo<M, C> ruleRepo;

	public RuleCompleteProxy(RuleRepo<M, C> ruleRepo) {
		this.ruleRepo = ruleRepo;
	}

	@Override
	public boolean introduce(M t) {
		for (int i = 0; i < this.ruleRepo.getIntroducers().size(); i++)
			if (!this.ruleRepo.getIntroducers().get(i).introduce(t))
				return false;
		return true;
	}

	@Override
	public boolean areConnectable(M prev, M next) {
		for (int i = 0; i < this.ruleRepo.getConnectionCheckers().size(); i++)
			if (!this.ruleRepo.getConnectionCheckers().get(i).areConnectable(prev, next))
				return false;
		return true;
	}

	@Override
	public boolean isExtensible(M model, C child) {
		for (int i = 0; i < this.ruleRepo.getExtensibilityCheckers().size(); i++)
			if (!this.ruleRepo.getExtensibilityCheckers().get(i).isExtensible(model, child))
				return false;
		return true;
	}

	@Override
	public ValidationStatus isValid(M t) {
		ValidationStatus res = ValidationStatus.valid;
		for (int i = 0; i < this.ruleRepo.getValidators().size(); i++) {
			ValidationStatus valState = this.ruleRepo.getValidators().get(i).isValid(t);
			if (valState == ValidationStatus.invalid)
				return ValidationStatus.invalid;
			else
				if (valState == ValidationStatus.extensible)
					res = ValidationStatus.extensible;
		}
		return res;
	}
}
