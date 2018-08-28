package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.AppendabilityChecker;

/**
 * Repository class for AppendabilityChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the stored rule implementations will be applied to.
 * @param <C> Type of the child model instances that the stored rule implementations will be applied to.
 * @see AppendabilityChecker
 */
public class AppendabilityCheckerRepository<P extends View, 
											C extends View>
				extends AbstractRuleRepository<AppendabilityChecker<P, C>> {
}