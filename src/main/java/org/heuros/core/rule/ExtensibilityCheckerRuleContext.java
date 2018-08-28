package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for ExtensibilityChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that the facade implementation will manage all rule related components for.
 * @see ExtensibilityChecker
 */
public interface ExtensibilityCheckerRuleContext<M extends View> extends RuleContext {
	public int registerExtensibilityCheckerRule(ExtensibilityChecker<M> rule) throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<M> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<M> getExtensibilityCheckerProxy();
}
