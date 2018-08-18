package org.heuros.rule;

import org.heuros.core.rule.AbstractRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.ExtensibilityCheckerRuleContext;
import org.heuros.core.rule.ValidatorRuleContext;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.data.model.PairView;
import org.heuros.data.model.DutyView;
import org.heuros.exception.RuleAnnotationIsMissing;

public class PairRuleContext extends AbstractRuleContext
								implements ConnectionCheckerRuleContext<PairView>,
											ExtensibilityCheckerRuleContext<PairView, DutyView>,
											ValidatorRuleContext<PairView> {
	protected ConnectionCheckerRepository<PairView> connectionCheckerRepo = new ConnectionCheckerRepository<PairView>();
	protected ExtensibilityCheckerRepository<PairView, DutyView> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<PairView, DutyView>();
	protected ValidatorRepository<PairView> validatorRepo = new ValidatorRepository<PairView>();

	protected ConnectionCheckerProxy<PairView> connectionCheckerProxy = new ConnectionCheckerProxy<PairView>(this.connectionCheckerRepo);
	protected ExtensibilityCheckerProxy<PairView, DutyView> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<PairView, DutyView>(this.extensibilityCheckerRepo);
	protected ValidatorProxy<PairView> validatorProxy = new ValidatorProxy<PairView>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	public PairRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<PairView>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<PairView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<PairView, DutyView>) rule);
		return this;
	}

	@Override
	public PairRuleContext registerConnectionCheckerRule(ConnectionChecker<PairView> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public PairRuleContext registerValidatorRule(Validator<PairView> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public PairRuleContext registerExtensibilityCheckerRule(ExtensibilityChecker<PairView, DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ConnectionCheckerRepository<PairView> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<PairView> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public ExtensibilityCheckerRepository<PairView, DutyView> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ConnectionCheckerProxy<PairView> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<PairView> getValidatorProxy() {
		return this.validatorProxy;
	}

	@Override
	public ExtensibilityCheckerProxy<PairView, DutyView> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}