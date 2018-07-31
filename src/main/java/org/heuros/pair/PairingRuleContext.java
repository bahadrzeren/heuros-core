package org.heuros.pair;

import org.heuros.core.base.RuleContext;
import org.heuros.core.model.Duty;
import org.heuros.core.model.Leg;
import org.heuros.core.model.Line;
import org.heuros.core.model.Pair;
import org.heuros.core.rule.RuleCompleteProxy;
import org.heuros.core.rule.RuleRepo;
import org.heuros.core.rule.RuleRepository;
import org.heuros.core.rule.inf.Rule;

public class PairingRuleContext implements RuleContext {

	private RuleRepo<Leg, Duty> legRuleRepo;
	private RuleRepo<Duty, Pair> dutyRuleRepo;
	private RuleRepo<Pair, ?> pairRuleRepo;

	private RuleCompleteProxy<Leg, Duty> legRuleProxy;
	private RuleCompleteProxy<Duty, Pair> dutyRuleProxy;
	private RuleCompleteProxy<Pair, ?> pairRuleProxy;

	public PairingRuleContext() {
		this.legRuleRepo = new RuleRepository<Leg, Duty>();
		this.dutyRuleRepo = new RuleRepository<Duty, Pair>();
		this.pairRuleRepo = new RuleRepository<Pair, Object>();
		this.legRuleProxy = new RuleCompleteProxy<Leg, Duty>(legRuleRepo);
		this.dutyRuleProxy = new RuleCompleteProxy<Duty, Pair>(dutyRuleRepo);
		this.pairRuleProxy = new RuleCompleteProxy<Pair, Object>(pairRuleRepo);
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
