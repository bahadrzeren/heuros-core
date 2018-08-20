package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.Aggregator;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface AggregatorRuleContext<P extends Model, C extends View> {
	public AggregatorRuleContext<P, C> registerAggregatorRule(Aggregator<P, C> rule) throws RuleAnnotationIsMissing;
	public Aggregator<P, C> getAggregatorImpl();
	public AggregatorProxy<P, C> getAggregatorProxy();
}