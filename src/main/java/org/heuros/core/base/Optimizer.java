package org.heuros.core.base;

import java.util.List;

import org.heuros.core.rule.context.RuleContext;

public interface Optimizer<I, O> {
	public List<O> optimize(DataContext<I, O> dataContext
							, RuleContext ruleContext);
}
