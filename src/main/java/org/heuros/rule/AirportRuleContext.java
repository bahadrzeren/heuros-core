package org.heuros.rule;

import org.heuros.core.rule.AbstractRuleContext;
import org.heuros.core.rule.IntroducerRuleContext;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.data.model.Airport;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

public class AirportRuleContext extends AbstractRuleContext implements IntroducerRuleContext<Airport> {

	protected IntroducerRepository<Airport> introducerRepo = new IntroducerRepository<Airport>();
	protected IntroducerProxy<Airport> introducerProxy = new IntroducerProxy<Airport>(this.introducerRepo);

	private static Class<?>[] airportClass = {Airport.class};

	@SuppressWarnings("unchecked")
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing {
		int res = super.registerRule(rule);
		if (RuleUtil.implChecker.isImplemented(rule, Introducer.class, airportClass))
			res += this.registerIntroducerRule((Introducer<Airport>) rule);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int removeRule(Rule rule) {
		if (RuleUtil.implChecker.isImplemented(rule, Introducer.class, airportClass))
			return this.introducerRepo.removeRule((Introducer<Airport>) rule);
		return 0;
	}

	@Override
	public int registerIntroducerRule(Introducer<Airport> rule) throws RuleAnnotationIsMissing {
		return this.introducerRepo.registerRule(rule);
	}

	@Override
	public IntroducerRepository<Airport> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public IntroducerProxy<Airport> getIntroducerProxy() {
		return this.introducerProxy;
	}
}
