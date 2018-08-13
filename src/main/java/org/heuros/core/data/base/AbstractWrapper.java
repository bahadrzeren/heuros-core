package org.heuros.core.data.base;

public abstract class AbstractWrapper<M extends Model> implements Wrapper<M> {

//	protected RuleContext<Wrapper<M>, M> ruleContext;
	protected M wrappee;

	public AbstractWrapper(//	RuleContext<Wrapper<M>, M> ruleContext, 
							M wrappee) {
//		this.ruleContext = ruleContext;
		this.wrappee = wrappee;
	}

	@Override
	public M getWrappee() {
		return wrappee;
	}

}
