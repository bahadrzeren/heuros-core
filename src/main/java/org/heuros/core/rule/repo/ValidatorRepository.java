package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Validator;

/**
 * Repository class for Validator rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the stored rule implementations will be applied to.
 * @see Validator
 */
public class ValidatorRepository<V extends View>
						extends AbstractRuleRepository<Validator<V>> {
}
