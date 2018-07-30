package org.heuros.core.rule;

import org.heuros.core.rule.inf.Rule;

public class RuleWrapper<R> {
	private R ruleImpl;
	private Rule ruleMeta;

	public R getRuleImpl() {
		return ruleImpl;
	}
	public void setRuleImpl(R ruleImpl) {
		this.ruleImpl = ruleImpl;
	}
	public Rule getRuleMeta() {
		return ruleMeta;
	}
	public void setRuleMeta(Rule ruleMeta) {
		this.ruleMeta = ruleMeta;
	}

	private RuleWrapper() {
	}

	public static <R> RuleWrapper<R> getInstance(R ruleImpl) throws Exception {
		Rule ruleMeta = ruleImpl.getClass().getAnnotation(Rule.class);
		if (ruleMeta == null)
			throw new Exception("@Rule annotation could not be found!");
		RuleWrapper<R> ruleWrapper = new RuleWrapper<R>();
		ruleWrapper.ruleImpl = ruleImpl;
		ruleWrapper.ruleMeta = ruleMeta;
		return ruleWrapper;
	}
}
