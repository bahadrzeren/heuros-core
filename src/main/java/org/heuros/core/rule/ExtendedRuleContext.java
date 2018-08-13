package org.heuros.core.rule;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtendedRuleContext<M extends Model, 
										E extends Extension, 
										C extends Model, 
										X extends Extension>
					extends RuleContext<M, E> {
	public ExtendedRuleContext<M, E, C, X> registerExtensibilityCheckerRule(ExtensibilityChecker<M, E, C, X> rule) throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<M, E, C, X> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<M, E, C, X> getExtensibilityCheckerProxy();
}
