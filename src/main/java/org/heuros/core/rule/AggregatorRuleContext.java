package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.Aggregator;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.core.rule.repo.AggregatorRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface AggregatorRuleContext<P extends Model, C extends View> extends RuleContext {
	public AggregatorRuleContext<P, C> registerAggregatorRule(Aggregator<P, C> rule) throws RuleAnnotationIsMissing;
	public AggregatorRepository<P, C> getAggregatorRepo();
	public AggregatorProxy<P, C> getAggregatorProxy();
}
