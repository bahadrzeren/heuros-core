package org.heuros.processor.legwrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.heuros.core.base.Processor;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegExtensionFactory;
import org.heuros.data.model.LegModel;
import org.heuros.data.model.LegWrapper;
import org.heuros.data.model.LegWrapperFactory;
import org.heuros.rule.LegRuleContext;

public class LegProcessor implements Processor<LegModel, LegWrapper, LegModel, LegExtension>{

	protected LegExtensionFactory extensionFactory = new LegExtensionFactory();

	protected LegRuleContext ruleContext = new LegRuleContext();

	protected LegWrapperFactory wrapperFactory = new LegWrapperFactory(extensionFactory, ruleContext);

	@Override
	public List<LegWrapper> proceed(List<LegModel> input) {
		return input.stream()
				.map((legModel) -> wrapperFactory.createWrapper(legModel))
				.collect(Collectors.toList());
	}

}
