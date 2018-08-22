package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.StarterChecker;
import org.heuros.core.rule.proxy.StarterCheckerProxy;
import org.heuros.core.rule.repo.StarterCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface StarterCheckerRuleContext<P extends View, C extends View> {
	public StarterCheckerRuleContext<P, C> registerStarterCheckerRule(StarterChecker<P, C> rule) 
																			throws RuleAnnotationIsMissing;
	public StarterCheckerRepository<P, C> getStarterCheckerRepo();
	public StarterCheckerProxy<P, C> getStarterCheckerProxy();
}