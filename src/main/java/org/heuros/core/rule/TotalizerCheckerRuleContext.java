package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.TotalizerChecker;
import org.heuros.core.rule.proxy.TotalizerCheckerProxy;
import org.heuros.core.rule.repo.TotalizerCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for TotalizerChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the facade implementation will manage all rule related components for.
 * @see TotalizerChecker
 */
public interface TotalizerCheckerRuleContext<V extends View> extends RuleContext {
	public int registerTotalizerCheckerRule(TotalizerChecker<V> rule) throws RuleAnnotationIsMissing;
	public TotalizerCheckerRepository<V> getTotalizerCheckerRepo();
	public TotalizerCheckerProxy<V> getTotalizerCheckerProxy();
}
