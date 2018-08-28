package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Aggregator;

/**
 * Repository class for Aggregator rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the stored rule implementations will be applied to.
 * @param <C> Type of the child model instances that the stored rule implementations will be applied to.
 * @see Aggregator
 */
public class AggregatorRepository<P extends Model, C extends View>
						extends AbstractRuleRepository<Aggregator<P, C>> {
}