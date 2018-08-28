package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Validator;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for Validator facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the facade implementation will manage all rule related components for.
 * @see Validator
 */
public interface ValidatorRuleContext<V extends View> extends RuleContext {
	public int registerValidatorRule(Validator<V> rule) throws RuleAnnotationIsMissing;
	public ValidatorRepository<V> getValidatorRepo();
	public ValidatorProxy<V> getValidatorProxy();
}
