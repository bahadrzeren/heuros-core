package org.heuros.core.base;

import org.heuros.core.rule.RuleCompleteProxy;
import org.heuros.core.rule.RuleRepo;
import org.heuros.core.rule.inf.Rule;

public interface RuleContext {
	public <M, C> RuleRepo<M, C> getRuleRepo(Class<M> t);
	public <M, C> RuleCompleteProxy<M, C> getRuleProxy(Class<M> t);
	public <M> void registerRule(Rule<M> rule);
}
