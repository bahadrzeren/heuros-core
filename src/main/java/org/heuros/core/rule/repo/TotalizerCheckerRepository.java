package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.TotalizerChecker;

/**
 * Repository class for Validator rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the stored rule implementations will be applied to.
 * @see TotalizerChecker
 */
public class TotalizerCheckerRepository<V extends View>
						extends AbstractRuleRepository<TotalizerChecker<V>> {
}
