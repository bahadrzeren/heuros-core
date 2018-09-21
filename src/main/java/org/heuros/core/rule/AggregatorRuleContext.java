package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Aggregator;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for Aggregator facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the facade implementation will manage all rule related components for.
 * @param <C> Type of the child model instances that the facade implementation will manage all rule related components for.
 * @see Aggregator
 */
public interface AggregatorRuleContext<P extends Model, C extends View> extends RuleContext {
	public int registerAggregatorRule(Aggregator<P, C> rule) throws RuleAnnotationIsMissing;
//	public Aggregator<P, C> getAggregatorImpl();
	public AggregatorProxy<P, C> getAggregatorProxy();
}
