package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public abstract class AbstractWrapperFactory<M extends Model> implements WrapperFactory<M> {

	protected RuleContext<M> ruleContext;

	public AbstractWrapperFactory(RuleContext<M> ruleContext) {
		this.ruleContext = ruleContext;
	}

	@Override
	public Wrapper<M> createWrapper(M m) {
		// TODO Auto-generated method stub
		return null;
	}

}
