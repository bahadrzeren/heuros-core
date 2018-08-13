package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractExtendedRuleContext<W extends Wrapper<M>, M extends Model, R extends Wrapper<C>, C extends Model> extends AbstractRuleContext<W, M>
														implements ExtendedRuleContext<W, M, R, C> {

	protected ExtensibilityCheckerRepository<W, M, R, C> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<W, M, R, C>();
	protected ExtensibilityCheckerProxy<W, M, R, C> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<W, M, R, C>(this.extensibilityCheckerRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<W, M> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<W, M, R, C>) rule);
		return this;
	}

	@Override
	public ExtendedRuleContext<W, M, R, C> registerExtensibilityCheckerRule(ExtensibilityChecker<W, M, R, C> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<W, M, R, C> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<W, M, R, C> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}
