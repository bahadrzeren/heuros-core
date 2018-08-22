package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.proxy.AppendabilityCheckerProxy;
import org.heuros.core.rule.repo.AppendabilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface AppendabilityCheckerRuleContext<P extends View, C extends View> {
	public AppendabilityCheckerRuleContext<P, C> registerAppendabilityCheckerRule(AppendabilityChecker<P, C> rule) 
																			throws RuleAnnotationIsMissing;
	public AppendabilityCheckerRepository<P, C> getAppendabilityCheckerRepo();
	public AppendabilityCheckerProxy<P, C> getAppendabilityCheckerProxy();
}
