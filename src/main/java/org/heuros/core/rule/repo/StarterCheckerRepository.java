package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.StarterChecker;

/**
 * Repository class for StarterChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the stored rule implementations will be applied to.
 * @param <C> Type of the child model instances that the stored rule implementations will be applied to.
 * @see StarterChecker
 */
public class StarterCheckerRepository<P extends View, 
											C extends View>
				extends AbstractRuleRepository<StarterChecker<P, C>> {
}