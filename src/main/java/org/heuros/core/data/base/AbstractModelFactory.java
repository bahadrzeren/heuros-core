package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public abstract class AbstractModelFactory<M extends Model, V extends View> implements ModelFactory<M> {

	protected RuleContext<M, V> ruleContext;

	public AbstractModelFactory(RuleContext<M, V> ruleContext) {
		this.ruleContext = ruleContext;
	}
}
