package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtensibilityCheckerRuleContext<M extends View> {
	public ExtensibilityCheckerRuleContext<M> registerExtensibilityCheckerRule(ExtensibilityChecker<M> rule) 
																			throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<M> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<M> getExtensibilityCheckerProxy();
}
