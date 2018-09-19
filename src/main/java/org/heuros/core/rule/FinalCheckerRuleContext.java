package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.FinalChecker;
import org.heuros.core.rule.proxy.FinalCheckerProxy;
import org.heuros.core.rule.repo.FinalCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for TotalizerChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the facade implementation will manage all rule related components for.
 * @see FinalChecker
 */
public interface FinalCheckerRuleContext<V extends View> extends RuleContext {
	public int registerFinalCheckerRule(FinalChecker<V> rule) throws RuleAnnotationIsMissing;
	public FinalCheckerRepository<V> getFinalCheckerRepo();
	public FinalCheckerProxy<V> getFinalCheckerProxy();
}
