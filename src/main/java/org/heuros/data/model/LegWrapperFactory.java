package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapperFactory;
import org.heuros.core.data.base.ExtensionFactory;
import org.heuros.core.rule.RuleContext;

public class LegWrapperFactory extends AbstractWrapperFactory<LegModel, LegExtension> {

	public LegWrapperFactory(ExtensionFactory<LegExtension> extensionFactory,
								RuleContext<LegModel, LegExtension> ruleContext) {
		super(extensionFactory, ruleContext);
	}

	@Override
	public LegWrapper createWrapper(LegModel m) {
		return new LegWrapper(this.ruleContext, m);
	}

}
