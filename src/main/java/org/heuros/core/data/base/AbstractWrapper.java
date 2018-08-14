package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public class AbstractWrapper<M extends Model, E extends Extension> implements Wrapper<M, E> {

	protected M wrappee;
	protected E extension;

	public AbstractWrapper(RuleContext<M, E> ruleContext,
							M wrappee,
							E extension) {
		ruleContext.getIntroducerProxy().introduce(wrappee, extension);
		this.wrappee = wrappee;
		this.extension = extension;
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
