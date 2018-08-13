package org.heuros.core.rule;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractExtendedRuleContext<M extends Model, 
													E extends Extension, 
													C extends Model, 
													X extends Extension> extends AbstractRuleContext<M, E>
														implements ExtendedRuleContext<M, E, C, X> {

	protected ExtensibilityCheckerRepository<M, E, C, X> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<M, E, C, X>();
	protected ExtensibilityCheckerProxy<M, E, C, X> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<M, E, C, X>(this.extensibilityCheckerRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M, E> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<M, E, C, X>) rule);
		return this;
	}

	@Override
	public ExtendedRuleContext<M, E, C, X> registerExtensibilityCheckerRule(ExtensibilityChecker<M, E, C, X> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<M, E, C, X> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<M, E, C, X> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}
