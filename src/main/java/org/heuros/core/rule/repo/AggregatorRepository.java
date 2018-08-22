package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Aggregator;

public class AggregatorRepository<P extends Model, C extends View>
						extends AbstractRuleRepository<Aggregator<P, C>> {
}