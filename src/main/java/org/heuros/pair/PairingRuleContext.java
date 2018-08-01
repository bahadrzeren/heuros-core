package org.heuros.pair;

import org.heuros.core.base.RuleContext;
import org.heuros.core.model.Duty;
import org.heuros.core.model.Leg;
import org.heuros.core.model.Pair;
import org.heuros.core.rule.RuleCompleteProxy;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.repo.RuleRepo;
import org.heuros.core.rule.repo.RuleRepository;

public class PairingRuleContext implements RuleContext {

	private RuleRepo<Introducer<Leg>> legRuleIntroducerRepo;
	private RuleRepo<Duty, Pair> dutyRuleRepo;
	private RuleRepo<Pair, Pair> pairRuleRepo;

	private RuleCompleteProxy<Leg, Duty> legRuleProxy;
	private RuleCompleteProxy<Duty, Pair> dutyRuleProxy;
	private RuleCompleteProxy<Pair, Pair> pairRuleProxy;

	public PairingRuleContext() {
		this.legRuleRepo = new RuleRepository<Leg, Duty>();
		this.dutyRuleRepo = new RuleRepository<Duty, Pair>();
		this.pairRuleRepo = new RuleRepository<Pair, Pair>();
		this.legRuleProxy = new RuleCompleteProxy<Leg, Duty>(legRuleRepo);
		this.dutyRuleProxy = new RuleCompleteProxy<Duty, Pair>(dutyRuleRepo);
		this.pairRuleProxy = new RuleCompleteProxy<Pair, Pair>(pairRuleRepo);
	}

	@Override
	public <T, P> RuleRepo<T, P> getRuleRepo(Class<T> t) {
		return null;
	}

	@Override
	public <T, P> RuleCompleteProxy<T, P> getRuleProxy(Class<T> t) {
		return null;
	}

	@Override
	public void registerRule(Rule rule) {
	}	
}
