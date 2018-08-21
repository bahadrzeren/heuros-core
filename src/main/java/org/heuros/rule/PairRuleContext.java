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
import org.heuros.data.model.PairView;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Pair;
import org.heuros.exception.RuleAnnotationIsMissing;

public class PairRuleContext extends AbstractRuleContext
								implements AggregatorRuleContext<Pair, DutyView>,
											StarterCheckerRuleContext<PairView, DutyView>,
											ExtensibilityCheckerRuleContext<PairView>,
											ConnectionCheckerRuleContext<PairView>,
											AppendabilityCheckerRuleContext<PairView, DutyView>,
											ValidatorRuleContext<PairView> {

	private static Logger logger = Logger.getLogger(PairRuleContext.class);

	protected Aggregator<Pair, DutyView> aggregatorImpl;
	protected StarterCheckerRepository<PairView, DutyView> starterCheckerRepo = new StarterCheckerRepository<PairView, DutyView>();
	protected ConnectionCheckerRepository<PairView> connectionCheckerRepo = new ConnectionCheckerRepository<PairView>();
	protected ExtensibilityCheckerRepository<PairView> extensibilityCheckerRepo = new ExtensibilityCheckerRepository<PairView>();
	protected AppendabilityCheckerRepository<PairView, DutyView> appendabilityCheckerRepo = new AppendabilityCheckerRepository<PairView, DutyView>();
	protected ValidatorRepository<PairView> validatorRepo = new ValidatorRepository<PairView>();

	protected AggregatorProxy<Pair, DutyView> aggregatorProxy;
	protected StarterCheckerProxy<PairView, DutyView> starterCheckerProxy = new StarterCheckerProxy<PairView, DutyView>(this.starterCheckerRepo);
	protected ConnectionCheckerProxy<PairView> connectionCheckerProxy = new ConnectionCheckerProxy<PairView>(this.connectionCheckerRepo);
	protected ExtensibilityCheckerProxy<PairView> extensibilityCheckerProxy = new ExtensibilityCheckerProxy<PairView>(this.extensibilityCheckerRepo);
	protected AppendabilityCheckerProxy<PairView, DutyView> appendabilityCheckerProxy = new AppendabilityCheckerProxy<PairView, DutyView>(this.appendabilityCheckerRepo);
	protected ValidatorProxy<PairView> validatorProxy = new ValidatorProxy<PairView>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	public PairRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(Aggregator.class))
			this.registerAggregatorRule((Aggregator<Pair, DutyView>) rule);
		if (rule.isImplemented(StarterChecker.class))
			this.registerStarterCheckerRule((StarterChecker<PairView, DutyView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.registerExtensibilityCheckerRule((ExtensibilityChecker<PairView>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<PairView>) rule);
		if (rule.isImplemented(AppendabilityChecker.class))
			this.registerAppendabilityCheckerRule((AppendabilityChecker<PairView, DutyView>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<PairView>) rule);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeRule(Rule rule) {
		if (rule.isImplemented(Aggregator.class))
			this.aggregatorImpl = null;
		if (rule.isImplemented(StarterChecker.class))
			this.starterCheckerRepo.removeRule((StarterChecker<PairView, DutyView>) rule);
		if (rule.isImplemented(ExtensibilityChecker.class))
			this.extensibilityCheckerRepo.removeRule((ExtensibilityChecker<PairView>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.connectionCheckerRepo.removeRule((ConnectionChecker<PairView>) rule);
		if (rule.isImplemented(AppendabilityChecker.class))
			this.appendabilityCheckerRepo.removeRule((AppendabilityChecker<PairView, DutyView>) rule);
		if (rule.isImplemented(Validator.class))
			this.validatorRepo.removeRule((Validator<PairView>) rule);
	}

	/*
	 * Aggregator context impl.
	 */
	@Override
	public AggregatorRuleContext<Pair, DutyView> registerAggregatorRule(Aggregator<Pair, DutyView> rule)
			throws RuleAnnotationIsMissing {
		if (this.aggregatorProxy == null) {
			this.aggregatorImpl = rule;
			this.aggregatorProxy = new AggregatorProxy<Pair, DutyView>(rule);
		} else
			logger.error("Rule impl is already registered!");
		return this;
	}

	@Override
	public Aggregator<Pair, DutyView> getAggregatorImpl() {
		return this.aggregatorImpl;
	}

	@Override
	public AggregatorProxy<Pair, DutyView> getAggregatorProxy() {
		return this.aggregatorProxy;
	}

	/*
	 * Starter context impl.
	 */
	@Override
	public PairRuleContext registerStarterCheckerRule(StarterChecker<PairView, DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.starterCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public StarterCheckerRepository<PairView, DutyView> getStarterCheckerRepo() {
		return this.starterCheckerRepo;
	}

	@Override
	public StarterCheckerProxy<PairView, DutyView> getStarterCheckerProxy() {
		return this.starterCheckerProxy;
	}

	/*
	 * ExtensibilityChecker context impl.
	 */
	@Override
	public PairRuleContext registerExtensibilityCheckerRule(ExtensibilityChecker<PairView> rule)
			throws RuleAnnotationIsMissing {
		this.extensibilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ExtensibilityCheckerRepository<PairView> getExtensibilityCheckerRepo() {
		return this.extensibilityCheckerRepo;
	}

	@Override
	public ExtensibilityCheckerProxy<PairView> getExtensibilityCheckerProxy() {
		return this.extensibilityCheckerProxy;
	}

	/*
	 * ConnectionChecker context impl.
	 */
	@Override
	public PairRuleContext registerConnectionCheckerRule(ConnectionChecker<PairView> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public ConnectionCheckerRepository<PairView> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ConnectionCheckerProxy<PairView> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	/*
	 * AppendabilityChecker context impl.
	 */
	@Override
	public PairRuleContext registerAppendabilityCheckerRule(AppendabilityChecker<PairView, DutyView> rule)
			throws RuleAnnotationIsMissing {
		this.appendabilityCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public AppendabilityCheckerRepository<PairView, DutyView> getAppendabilityCheckerRepo() {
		return this.appendabilityCheckerRepo;
	}

	@Override
	public AppendabilityCheckerProxy<PairView, DutyView> getAppendabilityCheckerProxy() {
		return this.appendabilityCheckerProxy;
	}

	/*
	 * Validator context impl.
	 */
	@Override
	public PairRuleContext registerValidatorRule(Validator<PairView> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public ValidatorRepository<PairView> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public ValidatorProxy<PairView> getValidatorProxy() {
		return this.validatorProxy;
	}
}
