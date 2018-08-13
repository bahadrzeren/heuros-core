package org.heuros.core.data.base;

public abstract class AbstractWrapperFactory<M extends Model, E extends Extension> 
														implements WrapperFactory<M, E> {

	protected ExtensionFactory<E> extensionFactory;
//	protected RuleContext<Wrapper<M>, M> ruleContext;

	public AbstractWrapperFactory(ExtensionFactory<E> extensionFactory
									//	RuleContext<Wrapper<M>, M> ruleContext
									) {
		this.extensionFactory = extensionFactory;
//		this.ruleContext = ruleContext;
	}
}
