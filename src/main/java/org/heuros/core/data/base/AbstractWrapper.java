package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public class AbstractWrapper<M extends Model, E extends Extension> implements Wrapper<M, E> {

	protected RuleContext<M, E> ruleContext;
	protected M wrappee;
	protected E extension;

	public AbstractWrapper(RuleContext<M, E> ruleContext, 
							M wrappee) {
		this.ruleContext = ruleContext;
		this.wrappee = wrappee;
	}

	@Override
	public M getWrappee() {
		return this.wrappee;
	}

	@Override
	public E getExtension() {
		return this.extension;
	}
}
