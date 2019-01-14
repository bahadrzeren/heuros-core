package org.heuros.rule;

import org.apache.log4j.Logger;
import org.heuros.core.rule.AggregatorRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.ExtensibilityCheckerRuleContext;
import org.heuros.core.rule.StarterCheckerRuleContext;
import org.heuros.core.rule.AppendabilityCheckerRuleContext;
import org.heuros.core.rule.FinalCheckerRuleContext;
import org.heuros.core.rule.intf.Aggregator;
import org.heuros.core.rule.intf.AppendabilityChecker;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.ExtensibilityChecker;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.intf.StarterChecker;
import org.heuros.core.rule.intf.FinalChecker;
import org.heuros.core.rule.proxy.AggregatorProxy;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.ExtensibilityCheckerProxy;
import org.heuros.core.rule.proxy.StarterCheckerProxy;
import org.heuros.core.rule.proxy.AppendabilityCheckerProxy;
import org.heuros.core.rule.proxy.FinalCheckerProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.core.rule.repo.StarterCheckerRepository;
import org.heuros.core.rule.repo.AppendabilityCheckerRepository;
import org.heuros.core.rule.repo.FinalCheckerRepository;
import org.heuros.data.model.PairView;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Pair;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

/**
 * Facade pattern to be used all kind of Pair model related rule operations.
 * 
 * @author bahadrzeren
 *
 */
public class PairRuleContext implements AggregatorRuleContext<Pair, Duty>,
											StarterCheckerRuleContext<PairView, DutyView>,
											ExtensibilityCheckerRuleContext<PairView>,
											ConnectionCheckerRuleContext<PairView>,
											AppendabilityCheckerRuleContext<PairView, DutyView>,
											FinalCheckerRuleContext<PairView> {

	private static Logger logger = Logger.getLogger(PairRuleContext.class);

	private int numOfBases = 0;

	protected Aggregator<Pair, Duty> aggregatorImpl = null;
	protected StarterCheckerRepository<PairView, DutyView> starterCheckerRepo = null;
	protected ConnectionCheckerRepository<PairView> connectionCheckerRepo = null;
	protected ExtensibilityCheckerRepository<PairView> extensibilityCheckerRepo = null;
	protected AppendabilityCheckerRepository<PairView, DutyView> appendabilityCheckerRepo = null;
	protected FinalCheckerRepository<PairView> finalCheckerRepo = null;

	protected AggregatorProxy<Pair, Duty> aggregatorProxy = null;
	protected StarterCheckerProxy<PairView, DutyView> starterCheckerProxy = null;
	protected ConnectionCheckerProxy<PairView> connectionCheckerProxy = null;
	protected ExtensibilityCheckerProxy<PairView> extensibilityCheckerProxy = null;
	protected AppendabilityCheckerProxy<PairView, DutyView> appendabilityCheckerProxy = null;
	protected FinalCheckerProxy<PairView> finalCheckerProxy = null;

	private static Class<?>[] pairViewClass = {PairView.class};
	private static Class<?>[] pairDutyClasses = {Pair.class, Duty.class};
	private static Class<?>[] pairViewDutyViewClasses = {PairView.class, DutyView.class};

	public PairRuleContext(int numOfBases) {
		this.numOfBases = numOfBases;
		this.starterCheckerRepo = new StarterCheckerRepository<PairView, DutyView>();
		this.connectionCheckerRepo = new ConnectionCheckerRepository<PairView>();
		this.extensibilityCheckerRepo = new ExtensibilityCheckerRepository<PairView>();
		this.appendabilityCheckerRepo = new AppendabilityCheckerRepository<PairView, DutyView>();
		this.finalCheckerRepo = new FinalCheckerRepository<PairView>();
		this.starterCheckerProxy = new StarterCheckerProxy<PairView, DutyView>(this.starterCheckerRepo, this.numOfBases);
		this.connectionCheckerProxy = new ConnectionCheckerProxy<PairView>(this.connectionCheckerRepo, this.numOfBases);
		this.extensibilityCheckerProxy = new ExtensibilityCheckerProxy<PairView>(this.extensibilityCheckerRepo, this.numOfBases);
		this.appendabilityCheckerProxy = new AppendabilityCheckerProxy<PairView, DutyView>(this.appendabilityCheckerRepo, this.numOfBases);
		this.finalCheckerProxy = new FinalCheckerProxy<PairView>(this.finalCheckerRepo, this.numOfBases);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Aggregator.class, pairDutyClasses))
			res += this.registerAggregatorRule((Aggregator<Pair, Duty>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, StarterChecker.class, pairViewDutyViewClasses))
			res += this.registerStarterCheckerRule((StarterChecker<PairView, DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ExtensibilityChecker.class, pairViewClass))
			res += this.registerExtensibilityCheckerRule((ExtensibilityChecker<PairView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, pairViewClass))
			res += this.registerConnectionCheckerRule((ConnectionChecker<PairView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, AppendabilityChecker.class, pairViewDutyViewClasses))
			res += this.registerAppendabilityCheckerRule((AppendabilityChecker<PairView, DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, FinalChecker.class, pairViewClass))
			res += this.registerFinalCheckerRule((FinalChecker<PairView>) rule);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeRule(Rule rule) {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Aggregator.class, pairDutyClasses))
			if (this.aggregatorImpl != null) {
				this.aggregatorImpl = null;
				res++;
			}
		if (RuleUtil.implChecker.isImplemented(rule, StarterChecker.class, pairViewDutyViewClasses))
			res += this.starterCheckerRepo.removeRule((StarterChecker<PairView, DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ExtensibilityChecker.class, pairViewClass))
			res += this.extensibilityCheckerRepo.removeRule((ExtensibilityChecker<PairView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, pairViewClass))
			res += this.connectionCheckerRepo.removeRule((ConnectionChecker<PairView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, AppendabilityChecker.class, pairViewDutyViewClasses))
			res += this.appendabilityCheckerRepo.removeRule((AppendabilityChecker<PairView, DutyView>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, FinalChecker.class, pairViewClass))
			res += this.finalCheckerRepo.removeRule((FinalChecker<PairView>) rule);
		return res;
	}

	/*
	 * Aggregator context impl.
	 */
	@Override
	public int registerAggregatorRule(Aggregator<Pair, Duty> rule)
			throws RuleAnnotationIsMissing {
		if (this.aggregatorProxy == null) {
			this.aggregatorImpl = rule;
			this.aggregatorProxy = new AggregatorProxy<Pair, Duty>(rule);
			return 1;
		} else
			logger.error("Rule impl is already registered!");
		return 0;
	}

//	@Override
//	public Aggregator<Pair, DutyView> getAggregatorImpl() {
//		return this.aggregatorImpl;
//	}

	@Override
	public AggregatorProxy<Pair, Duty> getAggregatorProxy() {
		return this.aggregatorProxy;
	}

	/*
	 * Starter context impl.
	 */
	@Override
	public int registerStarterCheckerRule(StarterChecker<PairView, DutyView> rule)
			throws RuleAnnotationIsMissing {
		return this.starterCheckerRepo.registerRule(rule);
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
	public int registerExtensibilityCheckerRule(ExtensibilityChecker<PairView> rule)
			throws RuleAnnotationIsMissing {
		return this.extensibilityCheckerRepo.registerRule(rule);
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
	public int registerConnectionCheckerRule(ConnectionChecker<PairView> rule)
			throws RuleAnnotationIsMissing {
		return this.connectionCheckerRepo.registerRule(rule);
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
	public int registerAppendabilityCheckerRule(AppendabilityChecker<PairView, DutyView> rule)
			throws RuleAnnotationIsMissing {
		return this.appendabilityCheckerRepo.registerRule(rule);
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
	 * TotalizerChecker context impl.
	 */
	@Override
	public int registerFinalCheckerRule(FinalChecker<PairView> rule) throws RuleAnnotationIsMissing {
		return this.finalCheckerRepo.registerRule(rule);
	}

	@Override
	public FinalCheckerRepository<PairView> getFinalCheckerRepo() {
		return this.finalCheckerRepo;
	}

	@Override
	public FinalCheckerProxy<PairView> getFinalCheckerProxy() {
		return this.finalCheckerProxy;
	}
}
