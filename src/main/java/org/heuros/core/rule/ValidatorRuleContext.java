package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Validator;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ValidatorRuleContext<V extends View> {
	public int registerValidatorRule(Validator<V> rule) throws RuleAnnotationIsMissing;
	public ValidatorRepository<V> getValidatorRepo();
	public ValidatorProxy<V> getValidatorProxy();
}
