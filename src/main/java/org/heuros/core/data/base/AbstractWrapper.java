package org.heuros.core.data.base;

public class AbstractWrapper<M extends Model, E extends Extension> implements Wrapper<M, E> {

//	protected RuleContext<Wrapper<M>, M> ruleContext;
	protected M wrappee;
	protected E extension;

	public AbstractWrapper(//	RuleContext<Wrapper<M>, M> ruleContext, 
							M wrappee) {
//		this.ruleContext = ruleContext;
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
