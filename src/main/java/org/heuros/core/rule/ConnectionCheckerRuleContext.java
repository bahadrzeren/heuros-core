package org.heuros.core.rule;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ConnectionCheckerRuleContext<V extends View> {
	public ConnectionCheckerRuleContext<V> registerConnectionCheckerRule(ConnectionChecker<V> rule) throws RuleAnnotationIsMissing;
	public ConnectionCheckerRepository<V> getConnectionCheckerRepo();
	public ConnectionCheckerProxy<V> getConnectionCheckerProxy();
}
