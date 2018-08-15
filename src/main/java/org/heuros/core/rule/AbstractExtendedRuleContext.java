package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractExtendedRuleContext<M extends Model, 
													V extends View, 
													C extends View> extends AbstractRuleContext<M, V>
														implements ExtendedRuleContext<M, V, C> {

	protected ExtensibilityCheckerRepository<V, C> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<V, C>();
	protected ExtensibilityCheckerProxy<V, C> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<V, C>(this.extensibilityCheckerRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M, V> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<V, C>) rule);
		return this;
	}

	@Override
	public ExtendedRuleContext<M, V, C> registerExtensibilityCheckerRule(ExtensibilityChecker<V, C> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<V, C> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<V, C> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}
