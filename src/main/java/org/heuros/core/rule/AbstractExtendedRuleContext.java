package org.heuros.core.rule;

import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractExtendedRuleContext<M, C> extends AbstractRuleContext<M>
														implements ExtendedRuleContext<M, C> {

	protected ExtensibilityCheckerRepository<M, C> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<M, C>();
	protected ExtensibilityCheckerProxy<M, C> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<M, C>(this.extensibilityCheckerRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<M, C>) rule);
		return this;
	}

	@Override
	public ExtendedRuleContext<M, C> registerExtensibilityCheckerRule(ExtensibilityChecker<M, C> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<M, C> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<M, C> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}
