package org.heuros.rule;

import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.IntroducerRuleContext;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

/**
 * Facade pattern to be used all kind of Leg model related rule operations.
 * 
 * @author bahadrzeren
 *
 */
public class LegRuleContext implements IntroducerRuleContext<Leg>,
										ConnectionCheckerRuleContext<LegView>{

	private int numOfBases = 0;

	protected IntroducerRepository<Leg> introducerRepo = new IntroducerRepository<Leg>();
	protected ConnectionCheckerRepository<LegView> connectionCheckerRepo = new ConnectionCheckerRepository<LegView>();

	protected IntroducerProxy<Leg> introducerProxy = new IntroducerProxy<Leg>(this.introducerRepo);
	protected ConnectionCheckerProxy<LegView> connectionCheckerProxy = new ConnectionCheckerProxy<LegView>(this.connectionCheckerRepo, this.numOfBases);

	private static Class<?>[] legClass = {Leg.class};
	private static Class<?>[] legViewClass = {LegView.class};

	public LegRuleContext(int numOfBases) {
		this.numOfBases = numOfBases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Introducer.class, legClass))
			res += this.registerIntroducerRule((Introducer<Leg>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, legViewClass))
			res += this.registerConnectionCheckerRule((ConnectionChecker<LegView>) rule);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeRule(Rule rule) {
		int res = 0;
		if (RuleUtil.implChecker.isImplemented(rule, Introducer.class, legClass))
			res += this.introducerRepo.removeRule((Introducer<Leg>) rule);
		if (RuleUtil.implChecker.isImplemented(rule, ConnectionChecker.class, legViewClass))
			res += this.connectionCheckerRepo.removeRule((ConnectionChecker<LegView>) rule);
		return res;
	}

	@Override
	public int registerIntroducerRule(Introducer<Leg> rule)
			throws RuleAnnotationIsMissing {
		return this.introducerRepo.registerRule(rule);
	}

	@Override
	public int registerConnectionCheckerRule(ConnectionChecker<LegView> rule)
			throws RuleAnnotationIsMissing {
		return this.connectionCheckerRepo.registerRule(rule);
	}

	@Override
	public IntroducerRepository<Leg> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public ConnectionCheckerRepository<LegView> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public IntroducerProxy<Leg> getIntroducerProxy() {
		return this.introducerProxy;
	}

	@Override
	public ConnectionCheckerProxy<LegView> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}
}