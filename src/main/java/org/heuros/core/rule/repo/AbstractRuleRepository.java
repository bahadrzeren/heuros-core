package org.heuros.core.rule.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

public class AbstractRuleRepository<R> implements RuleRepository<R> {

	private Logger logger = Logger.getLogger(AbstractRuleRepository.class);

	protected List<R> rules = new ArrayList<R>();

	@Override
	public int registerRule(R rule) throws RuleAnnotationIsMissing {
		if (this.rules.stream()
						.filter((i) -> i == rule)
						.findFirst()
						.isPresent()) {
			logger.error("Rule impl is already registered!");
			return 0;
		} else {
			if (RuleUtil.ruleAnnotationGetter.getRuleImplementation(((Rule) rule)) == null)
				throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
			this.rules.add(rule);
			return 1;
		}
	}

	@Override
	public List<R> getRules() {
		return this.rules;
	}

	@Override
	public int removeRule(R rule) {
		if (this.rules.stream()
				.filter((i) -> i == rule)
				.findFirst()
				.isPresent()) {
			this.rules.remove(rule);
			return 1;
		}
		logger.warn("Rule is not registered!");
		return 0;
	}
}
