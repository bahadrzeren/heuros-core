package org.heuros.core.data.base;

import org.heuros.core.rule.RuleContext;

public abstract class AbstractWrapperFactory<M extends Model, E extends Extension> 
														implements WrapperFactory<M, E> {

	protected ExtensionFactory<E> extensionFactory;
	protected RuleContext<M, E> ruleContext;

	public AbstractWrapperFactory(ExtensionFactory<E> extensionFactory,
									RuleContext<M, E> ruleContext) {
		this.extensionFactory = extensionFactory;
		this.ruleContext = ruleContext;
	}
}
