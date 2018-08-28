package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ConnectionChecker;

/**
 * Repository class for ConnectionChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the stored rule implementations will be applied to.
 * @see ConnectionChecker
 */
public class ConnectionCheckerRepository<V extends View>
						extends AbstractRuleRepository<ConnectionChecker<V>> {
}
