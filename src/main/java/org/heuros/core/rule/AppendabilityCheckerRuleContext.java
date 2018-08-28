package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.proxy.AppendabilityCheckerProxy;
import org.heuros.core.rule.repo.AppendabilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for AppendabilityChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the facade implementation will manage all rule related components for.
 * @param <C> Type of the child model instances that the facade implementation will manage all rule related components for.
 * @see AppendabilityChecker
 */
public interface AppendabilityCheckerRuleContext<P extends View, C extends View> extends RuleContext {
	public int registerAppendabilityCheckerRule(AppendabilityChecker<P, C> rule) throws RuleAnnotationIsMissing;
	public AppendabilityCheckerRepository<P, C> getAppendabilityCheckerRepo();
	public AppendabilityCheckerProxy<P, C> getAppendabilityCheckerProxy();
}
