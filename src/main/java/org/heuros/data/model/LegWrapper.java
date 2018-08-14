package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.rule.RuleContext;

public class LegWrapper extends AbstractWrapper<LegModel, LegExtension> {

	public LegWrapper(RuleContext<LegModel, LegExtension> legRuleContext,
						LegModel wrappee,
						LegExtension extension) {
		super(legRuleContext, wrappee, extension);
	}

	@Override
	public String toString() {
		return this.getWrappee().toString();
	}
}
