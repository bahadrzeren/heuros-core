package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface ExtendedRuleContext<W extends Wrapper<M>, M extends Model, R extends Wrapper<C>, C extends Model> extends RuleContext<W, M> {
	public ExtendedRuleContext<W, M, R, C> registerExtensibilityCheckerRule(ExtensibilityChecker<W, M, R, C> rule) throws RuleAnnotationIsMissing;
	public ExtensibilityCheckerRepository<W, M, R, C> getExtensibilityCheckerRepo();
	public ExtensibilityCheckerProxy<W, M, R, C> getExtensibilityCheckerProxy();
}
