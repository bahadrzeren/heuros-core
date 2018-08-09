package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public abstract class AbstractWrapper<M extends Model> implements Wrapper<M> {

	protected RuleContext<M> ruleContext;
	protected M wrappee;

	public AbstractWrapper(RuleContext<M> ruleContext, M wrappee) {
		this.ruleContext = ruleContext;
		this.wrappee = wrappee;
	}

	@Override
	public M getWrappee() {
		return wrappee;
	}

}
