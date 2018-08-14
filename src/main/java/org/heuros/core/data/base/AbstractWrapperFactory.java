package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public abstract class AbstractWrapperFactory<M extends Model, E extends Extension> 
														implements WrapperFactory<M, E> {

	protected RuleContext<M, E> ruleContext;
	protected ExtensionFactory<E> extensionFactory;

	public AbstractWrapperFactory(RuleContext<M, E> ruleContext,
									ExtensionFactory<E> extensionFactory) {
		this.ruleContext = ruleContext;
		this.extensionFactory = extensionFactory;
	}
}
