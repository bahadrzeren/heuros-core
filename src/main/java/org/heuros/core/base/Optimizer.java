package org.heuros.core.base;

import java.util.List;

import org.heuros.core.modelbase.IModel;

public interface Optimizer<I extends IModel, O extends IModel> {
	public List<O> optimize(DataContext<I, O> dataContext
							, RuleContext ruleContext);
}
