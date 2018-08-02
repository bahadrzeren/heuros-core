package org.heuros.core.rule.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.rule.inf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Rule repository generic implementation.
 * 
 * @author bahadrzeren
 *
 * @param <R>
 * @param <M>
 */
public class RuleRepository<R extends Rule, M> implements RuleRepo<R, M> {

	private Logger logger = Logger.getLogger(RuleRepository.class);

	protected Class<?> ruleInterfaceType;
	protected Class<M> modelType;
	protected List<R> rules = new ArrayList<R>();

	/**
	 * Class<R> did not work for ruleInterfaceType!
	 * To make it working Class<?> is used.
	 * Design should be rethought.
	 * 
	 * @param ruleInterfaceType
	 * @param modelType
	 */
	public RuleRepository(Class<?> ruleInterfaceType, Class<M> modelType) {
		this.ruleInterfaceType = ruleInterfaceType;
		this.modelType = modelType;
	}

	@Override
	public void registerRule(R rule) throws RuleAnnotationIsMissing {
		if (this.rules.stream()
						.filter((i) -> i == rule)
						.findFirst()
						.isPresent())
			logger.error("Rule impl is already registered!");
		else {
			if (rule.getAnnotation() == null)
				throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
			this.rules.add(rule);
		}
	}

	@Override
	public List<R> getRules() {
		return this.rules;
	}

	@Override
	public Class<?> getRuleInterfaceType() {
		return this.ruleInterfaceType;
	}

	@Override
	public Class<M> getModelType() {
		return this.modelType;
	}
}
