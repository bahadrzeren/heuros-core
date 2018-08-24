package org.heuros.rule;

import org.apache.log4j.Logger;
import org.heuros.core.rule.AggregatorRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.ExtensibilityCheckerRuleContext;
import org.heuros.core.rule.StarterCheckerRuleContext;
import org.heuros.core.rule.AppendabilityCheckerRuleContext;
import org.heuros.core.rule.ValidatorRuleContext;
import org.heuros.core.rule.intf.Aggregator;
import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.ExtensibilityChecker;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.intf.StarterChecker;
import org.heuros.core.rule.intf.Validator;
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
import org.heuros.util.RuleUtil;

public class DutyRuleContext implements AggregatorRuleContext<Duty, LegView>,
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

	private static Class<?>[] dutyViewClass = {DutyView.class};
	private static Class<?>[] dutyLegViewClasses = {Duty.class, LegView.class};
	private static Class<?>[] dutyViewLegViewClasses = {DutyView.class, LegView.class};

	@SuppressWarnings("unchecked")
	@Override
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Aggregator.class, dutyLegViewClasses))
			res += this.registerAggregatorRule((Aggregator<Duty, LegView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, StarterChecker.class, dutyViewLegViewClasses))
			res += this.registerStarterCheckerRule((StarterChecker<DutyView, LegView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ExtensibilityChecker.class, dutyViewClass))
			res += this.registerExtensibilityCheckerRule((ExtensibilityChecker<DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, dutyViewClass))
			res += this.registerConnectionCheckerRule((ConnectionChecker<DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, AppendabilityChecker.class, dutyViewLegViewClasses))
			res += this.registerAppendabilityCheckerRule((AppendabilityChecker<DutyView, LegView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, Validator.class, dutyViewClass))
			res += this.registerValidatorRule((Validator<DutyView>) rule);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeRule(Rule rule) {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Aggregator.class, dutyLegViewClasses))
			if (this.aggregatorImpl != null) {
				this.aggregatorImpl = null;
				res++;
			}
		if (RuleUtil.implChecker.isImplemented(rule, StarterChecker.class, dutyViewLegViewClasses))
			res += this.starterCheckerRepo.removeRule((StarterChecker<DutyView, LegView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ExtensibilityChecker.class, dutyViewClass))
			res += this.extensibilityCheckerRepo.removeRule((ExtensibilityChecker<DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, dutyViewClass))
			res += this.connectionCheckerRepo.removeRule((ConnectionChecker<DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, AppendabilityChecker.class, dutyViewLegViewClasses))
			res += this.appendabilityCheckerRepo.removeRule((AppendabilityChecker<DutyView, LegView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, Validator.class,dutyViewClass))
			res += this.validatorRepo.removeRule((Validator<DutyView>) rule);
		return res;
	}

	/*
	 * Aggregator context impl.
	 */
	@Override
	public int registerAggregatorRule(Aggregator<Duty, LegView> rule)
			throws RuleAnnotationIsMissing {
		if (this.aggregatorProxy == null) {
			this.aggregatorImpl = rule;
			this.aggregatorProxy = new AggregatorProxy<Duty, LegView>(rule);
			return 1;
		} else
			logger.error("Rule impl is already registered!");
		return 0;
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
	public int registerStarterCheckerRule(StarterChecker<DutyView, LegView> rule)
			throws RuleAnnotationIsMissing {
		return this.starterCheckerRepo.registerRule(rule);
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
	public int registerExtensibilityCheckerRule(ExtensibilityChecker<DutyView> rule)
			throws RuleAnnotationIsMissing {
		return this.extensibilityCheckerRepo.registerRule(rule);
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
	public int registerConnectionCheckerRule(ConnectionChecker<DutyView> rule)
			throws RuleAnnotationIsMissing {
		return this.connectionCheckerRepo.registerRule(rule);
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
	public int registerAppendabilityCheckerRule(AppendabilityChecker<DutyView, LegView> rule)
			throws RuleAnnotationIsMissing {
		return this.appendabilityCheckerRepo.registerRule(rule);
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
	public int registerValidatorRule(Validator<DutyView> rule) throws RuleAnnotationIsMissing {
		return this.validatorRepo.registerRule(rule);
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
