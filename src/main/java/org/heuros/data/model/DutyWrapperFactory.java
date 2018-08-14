package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapperFactory;
import org.heuros.core.data.base.ExtensionFactory;
import org.heuros.core.rule.RuleContext;

public class DutyWrapperFactory extends AbstractWrapperFactory<DutyModel, DutyExtension> {

	public DutyWrapperFactory(RuleContext<DutyModel, DutyExtension> ruleContext,
								ExtensionFactory<DutyExtension> extensionFactory) {
		super(ruleContext, extensionFactory);
	}

	@Override
	public DutyWrapper createWrapper(DutyModel m) {
		return new DutyWrapper(this.ruleContext, m, this.extensionFactory.createExtension());
	}

}
