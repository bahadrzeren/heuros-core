package org.heuros.core.rule.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.rule.inf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

public class AbstractRuleRepository<R> implements RuleRepository<R> {

	private Logger logger = Logger.getLogger(AbstractRuleRepository.class);

	protected List<R> rules = new ArrayList<R>();

	@Override
	public void registerRule(R rule) throws RuleAnnotationIsMissing {
		if (this.rules.stream()
						.filter((i) -> i == rule)
						.findFirst()
						.isPresent())
			logger.error("Rule impl is already registered!");
		else {
			if (((Rule) rule).getAnnotation() == null)
				throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
			this.rules.add(rule);
		}
	}

	@Override
	public List<R> getRules() {
		return this.rules;
	}

	@Override
	public void removeRule(R rule) {
		if (this.rules.stream()
				.filter((i) -> i == rule)
				.findFirst()
				.isPresent()) {
			this.rules.remove(rule);			
		} else {
			logger.error("Rule is not registered!");
		}
	}
}
