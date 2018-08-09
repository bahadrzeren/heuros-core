package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtendedRuleContext<M extends Model, C extends Model> extends RuleContext<M> {
	public ExtendedRuleContext<M, C> registerExtensibilityCheckerRule(ExtensibilityChecker<M, C> rule) throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<M, C> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<M, C> getExtensibilityCheckerProxy();
}
