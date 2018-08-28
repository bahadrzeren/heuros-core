package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.StarterChecker;
import org.heuros.core.rule.proxy.StarterCheckerProxy;
import org.heuros.core.rule.repo.StarterCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for StarterChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances that the facade implementation will manage all rule related components for.
 * @param <C> Type of the child model instances that the facade implementation will manage all rule related components for.
 * @see StarterChecker
 */
public interface StarterCheckerRuleContext<P extends View, C extends View> extends RuleContext {
	public int registerStarterCheckerRule(StarterChecker<P, C> rule) throws RuleAnnotationIsMissing;
	public StarterCheckerRepository<P, C> getStarterCheckerRepo();
	public StarterCheckerProxy<P, C> getStarterCheckerProxy();
}
