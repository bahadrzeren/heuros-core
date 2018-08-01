package org.heuros.core.rule.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.rule.inf.RuleImpl;

public class RuleRepository<R> implements RuleRepo<R> {

	private Logger logger = Logger.getLogger(RuleRepository.class);

	protected RuleImpl ruleMeta;
	protected List<R> rules = new ArrayList<R>();

	@Override
	public void registerRule(R rule) throws Exception {
		if (rules.stream().filter((i) -> i == rule)
				.findFirst()
				.isPresent())
			logger.error("Rule impl is already registered!");
		else {
			RuleImpl ruleMeta = rule.getClass().getAnnotation(RuleImpl.class);
			if (ruleMeta == null)
				throw new Exception("@Rule annotation could not be found!");
			this.ruleMeta = ruleMeta;
			rules.add(rule);
		}
	}

	@Override
	public List<R> getRules() {
		return this.rules;
	}

	@Override
	public RuleImpl getRuleMeta() {
		return this.ruleMeta;
	}
}
