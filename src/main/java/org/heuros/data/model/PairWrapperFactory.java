package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapperFactory;
import org.heuros.core.data.base.ExtensionFactory;
import org.heuros.core.rule.RuleContext;

public class PairWrapperFactory extends AbstractWrapperFactory<PairModel, PairExtension> {

	public PairWrapperFactory(RuleContext<PairModel, PairExtension> ruleContext,
								ExtensionFactory<PairExtension> extensionFactory) {
		super(ruleContext, extensionFactory);
	}

	@Override
	public PairWrapper createWrapper(PairModel m) {
		return new PairWrapper(this.ruleContext, m, this.extensionFactory.createExtension());
	}

}
