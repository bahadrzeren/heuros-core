package org.heuros.rule;

import org.apache.log4j.Logger;
import org.heuros.core.rule.AbstractRuleContext;
import org.heuros.core.rule.AggregatorRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.ExtensibilityCheckerRuleContext;
import org.heuros.core.rule.StarterCheckerRuleContext;
import org.heuros.core.rule.AppendabilityCheckerRuleContext;
import org.heuros.core.rule.ValidatorRuleContext;
import org.heuros.core.rule.inf.Aggregator;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.AppendabilityChecker;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.inf.StarterChecker;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.proxy.StarterCheckerProxy;
import org.heuros.core.rule.proxy.AppendabilityCheckerProxy;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.core.rule.repo.StarterCheckerRepository;
import org.heuros.core.rule.repo.AppendabilityCheckerRepository;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.LegView;
import org.heuros.exception.RuleAnnotationIsMissing;

public class DutyRuleContext extends AbstractRuleContext
								implements AggregatorRuleContext<Duty, LegView>,
											StarterCheckerRuleContext<DutyView, LegView>,
											ExtensibilityCheckerRuleContext<DutyView>,
											ConnectionCheckerRuleContext<DutyView>,
											AppendabilityCheckerRuleContext<DutyView, LegView>,
											ValidatorRuleContext<DutyView> {

	private static Logger logger = Logger.getLogger(DutyRuleContext.class);

	protected Aggregator<Duty, LegView> aggregatorImpl;
	protected StarterCheckerRepository<DutyView, LegView> starterCheckerRepo = new StarterCheckerRepository<DutyView, LegView>();
	protected ExtensibilityCheckerRepository<DutyView> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<DutyView>();
	protected ConnectionCheckerRepository<DutyView> connectionCheckerRepo = new ConnectionCheckerRepository<DutyView>();
	protected AppendabilityCheckerRepository<DutyView, LegView> appendabilityCheckerRepo = new AppendabilityCheckerRepository<DutyView, LegView>();
	protected ValidatorRepository<DutyView> validatorRepo = new ValidatorRepository<DutyView>();

	protected AggregatorProxy<Duty, LegView> aggregatorProxy;
	protected StarterCheckerProxy<DutyView, LegView> starterCheckerProxy = new StarterCheckerProxy<DutyView, LegView>(this.starterCheckerRepo);
	protected ExtensibilityCheckerProxy<DutyView> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<DutyView>(this.extensibilityCheckerRepo);
	protected ConnectionCheckerProxy<DutyView> connectionCheckerProxy = new ConnectionCheckerProxy<DutyView>(this.connectionCheckerRepo);
	protected AppendabilityCheckerProxy<DutyView, LegView> appendabilityCheckerProxy = new AppendabilityCheckerProxy<DutyView, LegView>(this.appendabilityCheckerRepo);
	protected ValidatorProxy<DutyView> validatorProxy = new ValidatorProxy<DutyView>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	@Override
	public DutyRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(Aggregator.class))
			this.registerAggregatorRule((Aggregator<Duty, LegView>) rule);
		if (rule.isImplemented(StarterChecker.class))
			this.registerStarterCheckerRule((StarterChecker<DutyView, LegView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<DutyView>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<DutyView>) rule);
		if (rule.isImplemented(AppendabilityChecker.class))
			this.registerAppendabilityCheckerRule((AppendabilityChecker<DutyView, LegView>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<DutyView>) rule);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeRule(Rule rule) {
		if (rule.isImplemented(Aggregator.class))
			this.aggregatorImpl = null;
		if (rule.isImplemented(StarterChecker.class))
			this.starterCheckerRepo.removeRule((StarterChecker<DutyView, LegView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.extensibilityCheckerRepo.removeRule((ExtensibilityChecker<DutyView>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.connectionCheckerRepo.removeRule((ConnectionChecker<DutyView>) rule);
		if (rule.isImplemented(AppendabilityChecker.class))
			this.appendabilityCheckerRepo.removeRule((AppendabilityChecker<DutyView, LegView>) rule);
		if (rule.isImplemented(Validator.class))
			this.validatorRepo.removeRule((Validator<DutyView>) rule);
	}

	/*
	 * Aggregator context impl.
	 */
	@Override
	public AggregatorRuleContext<Duty, LegView> registerAggregatorRule(Aggregator<Duty, LegView> rule)
			throws RuleAnnotationIsMissing {
		if (this.aggregatorProxy == null) {
			this.aggregatorImpl = rule;
			this.aggregatorProxy = new AggregatorProxy<Duty, LegView>(rule);
		} else
			logger.error("Rule impl is already registered!");
		return this;
	}

	@Override
	public Aggregator<Duty, LegView> getAggregatorImpl() {
		return this.aggregatorImpl;
	}

	@Override
	public AggregatorProxy<Duty, LegView> getAggregatorProxy() {
		return this.aggregatorProxy;
	}

	/*
	 * Starter context impl.
	 */
	@Override
	public DutyRuleContext registerStarterCheckerRule(StarterChecker<DutyView, LegView> rule)
			throws RuleAnnotationIsMissing {
		this.starterCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public StarterCheckerRepository<DutyView, LegView> getStarterCheckerRepo() {
		return this.starterCheckerRepo;
	}

	@Override
	public StarterCheckerProxy<DutyView, LegView> getStarterCheckerProxy() {
		return this.starterCheckerProxy;
	}

	/*
	 * ExtensibilityChecker context impl.
	 */
	@Override
	public DutyRuleContext registerExtensibilityCheckerRule(ExtensibilityChecker<DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<DutyView> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<DutyView> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}

	/*
	 * ConnectionChecker context impl.
	 */
	@Override
	public DutyRuleContext registerConnectionCheckerRule(ConnectionChecker<DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ConnectionCheckerRepository<DutyView> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ConnectionCheckerProxy<DutyView> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	/*
	 * AppendabilityChecker context impl.
	 */
	@Override
	public DutyRuleContext registerAppendabilityCheckerRule(AppendabilityChecker<DutyView, LegView> rule)
			throws RuleAnnotationIsMissing {
		this.appendabilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public AppendabilityCheckerRepository<DutyView, LegView> getAppendabilityCheckerRepo() {
		return this.appendabilityCheckerRepo;
	}

	@Override
	public AppendabilityCheckerProxy<DutyView, LegView> getAppendabilityCheckerProxy() {
		return this.appendabilityCheckerProxy;
	}

	/*
	 * Validator context impl.
	 */
	@Override
	public DutyRuleContext registerValidatorRule(Validator<DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public ValidatorRepository<DutyView> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public ValidatorProxy<DutyView> getValidatorProxy() {
		return this.validatorProxy;
	}

}
