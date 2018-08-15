package org.heuros.rule;

import org.heuros.core.rule.IntroducerRuleContext;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.data.model.Airport;
import org.heuros.exception.RuleAnnotationIsMissing;

public class AirportRuleContext implements IntroducerRuleContext<Airport> {

	protected IntroducerRepository<Airport> introducerRepo = new IntroducerRepository<Airport>();
	protected IntroducerProxy<Airport> introducerProxy = new IntroducerProxy<Airport>(this.introducerRepo);

	@SuppressWarnings("unchecked")
	public AirportRuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<Airport>) rule);
		return this;
	}

	@Override
	public AirportRuleContext registerIntroducerRule(Introducer<Airport> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
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
