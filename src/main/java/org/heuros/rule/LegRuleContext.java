package org.heuros.rule;

import org.heuros.core.rule.AbstractRuleContext;
import org.heuros.core.rule.ConnectionCheckerRuleContext;
import org.heuros.core.rule.IntroducerRuleContext;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.exception.RuleAnnotationIsMissing;

public class LegRuleContext extends AbstractRuleContext
							implements IntroducerRuleContext<Leg>,
										ConnectionCheckerRuleContext<LegView>{

	protected IntroducerRepository<Leg> introducerRepo = new IntroducerRepository<Leg>();
	protected ConnectionCheckerRepository<LegView> connectionCheckerRepo = new ConnectionCheckerRepository<LegView>();

	protected IntroducerProxy<Leg> introducerProxy = new IntroducerProxy<Leg>(this.introducerRepo);
	protected ConnectionCheckerProxy<LegView> connectionCheckerProxy = new ConnectionCheckerProxy<LegView>(this.connectionCheckerRepo);

	@SuppressWarnings("unchecked")
	public LegRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		super.registerRule(rule);
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<Leg>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<LegView>) rule);
		return this;
	}

	@Override
	public LegRuleContext registerIntroducerRule(Introducer<Leg> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public LegRuleContext registerConnectionCheckerRule(ConnectionChecker<LegView> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
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