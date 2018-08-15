package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtendedRuleContext<M extends Model, V extends View, C extends View>
																extends RuleContext<M, V> {
	public ExtendedRuleContext<M, V, C> registerExtensibilityCheckerRule(ExtensibilityChecker<V, C> rule) 
																			throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<V, C> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<V, C> getExtensibilityCheckerProxy();
}
