package org.heuros.processor.leg;

import java.util.List;
import java.util.stream.Collectors;

import org.heuros.core.base.Processor;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegExtensionFactory;
import org.heuros.data.model.LegModel;
import org.heuros.data.model.LegWrapper;
import org.heuros.data.model.LegWrapperFactory;
import org.heuros.rule.LegRuleContext;

public class LegProcessor implements Processor<LegModel, LegWrapper, LegModel, LegExtension> {

	protected LegRuleContext ruleContext;
	protected LegExtensionFactory extensionFactory;
	protected LegWrapperFactory wrapperFactory;

	public LegProcessor setRuleContext(LegRuleContext ruleContext) {
		this.ruleContext = ruleContext;
		return this;
	}

	public LegProcessor setExtensionFactory(LegExtensionFactory extensionFactory) {
		this.extensionFactory = extensionFactory;
		return this;
	}

	public LegProcessor setWrapperFactory(LegWrapperFactory wrapperFactory) {
		this.wrapperFactory = wrapperFactory;
		return this;
	}

	@Override
	public List<LegWrapper> proceed(List<LegModel> input) {
		return input.stream()
				.map((legModel) -> wrapperFactory.createWrapper(legModel))
				.collect(Collectors.toList());
	}
}
