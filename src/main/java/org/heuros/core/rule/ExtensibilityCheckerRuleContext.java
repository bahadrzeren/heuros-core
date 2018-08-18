package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtensibilityCheckerRuleContext<P extends View, C extends View> {
	public ExtensibilityCheckerRuleContext<P, C> registerExtensibilityCheckerRule(ExtensibilityChecker<P, C> rule) 
																			throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<P, C> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<P, C> getExtensibilityCheckerProxy();
}
