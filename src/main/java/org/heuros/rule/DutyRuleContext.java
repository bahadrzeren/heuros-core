package org.heuros.rule;

import org.heuros.core.rule.AggregatorRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.ExtensibilityCheckerRuleContext;
import org.heuros.core.rule.ValidatorRuleContext;
import org.heuros.core.rule.inf.Aggregator;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.AggregatorRepository;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.LegView;
import org.heuros.exception.RuleAnnotationIsMissing;

public class DutyRuleContext implements AggregatorRuleContext<Duty, DutyView>,
										ConnectionCheckerRuleContext<DutyView>,
										ExtensibilityCheckerRuleContext<DutyView, LegView>,
										ValidatorRuleContext<DutyView> {
	protected AggregatorRepository<Duty, DutyView> aggregatorRepo = new AggregatorRepository<Duty, DutyView>();
	protected ConnectionCheckerRepository<DutyView> connectionCheckerRepo = new ConnectionCheckerRepository<DutyView>();
	protected ExtensibilityCheckerRepository<DutyView, LegView> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<DutyView, LegView>();
	protected ValidatorRepository<DutyView> validatorRepo = new ValidatorRepository<DutyView>();

	protected AggregatorProxy<Duty, DutyView> aggregatorProxy = new AggregatorProxy<Duty, DutyView>(this.aggregatorRepo);
	protected ConnectionCheckerProxy<DutyView> connectionCheckerProxy = new ConnectionCheckerProxy<DutyView>(this.connectionCheckerRepo);
	protected ExtensibilityCheckerProxy<DutyView, LegView> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<DutyView, LegView>(this.extensibilityCheckerRepo);
	protected ValidatorProxy<DutyView> validatorProxy = new ValidatorProxy<DutyView>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	public DutyRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Aggregator.class))
			this.registerAggregatorRule((Aggregator<Duty, DutyView>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<DutyView>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<DutyView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<DutyView, LegView>) rule);
		return this;
	}

	@Override
	public AggregatorRuleContext<Duty, DutyView> registerAggregatorRule(Aggregator<Duty, DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.aggregatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public DutyRuleContext registerConnectionCheckerRule(ConnectionChecker<DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public DutyRuleContext registerValidatorRule(Validator<DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public DutyRuleContext registerExtensibilityCheckerRule(ExtensibilityChecker<DutyView, LegView> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public AggregatorRepository<Duty, DutyView> getAggregatorRepo() {
		return this.aggregatorRepo;
	}

	@Override
	public ConnectionCheckerRepository<DutyView> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<DutyView> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public ExtensibilityCheckerRepository<DutyView, LegView> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public AggregatorProxy<Duty, DutyView> getAggregatorProxy() {
		return this.aggregatorProxy;
	}

	@Override
	public ConnectionCheckerProxy<DutyView> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<DutyView> getValidatorProxy() {
		return this.validatorProxy;
	}

	@Override
	public ExtensibilityCheckerProxy<DutyView, LegView> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}
}
