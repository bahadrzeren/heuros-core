package org.heuros.core.rule.repo;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ExtensibilityChecker;

/**
 * Repository class for ExtensibilityChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that the stored rule implementations will be applied to.
 * @see ExtensibilityChecker
 */
public class ExtensibilityCheckerRepository<M extends View>
				extends AbstractRuleRepository<ExtensibilityChecker<M>> {
}
