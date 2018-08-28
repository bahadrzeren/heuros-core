package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Interface for ConnectionChecker facade implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that the facade implementation will manage all rule related components for.
 * @see ConnectionChecker
 */
public interface ConnectionCheckerRuleContext<V extends View> extends RuleContext {
	public int registerConnectionCheckerRule(ConnectionChecker<V> rule) throws RuleAnnotationIsMissing;
	public ConnectionCheckerRepository<V> getConnectionCheckerRepo();
	public ConnectionCheckerProxy<V> getConnectionCheckerProxy();
}
