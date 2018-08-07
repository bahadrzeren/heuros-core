package org.heuros.core.rule;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.proxy.ConnectionCheckerProxy;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.proxy.ValidatorProxy;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractRuleContext<M> implements RuleContext<M> {

	protected IntroducerRepository<M> introducerRepo = new IntroducerRepository<M>();
	protected ConnectionCheckerRepository<M> connectionCheckerRepo = new ConnectionCheckerRepository<M>();
	protected ValidatorRepository<M> validatorRepo = new ValidatorRepository<M>();

	protected IntroducerProxy<M> introducerProxy = new IntroducerProxy<M>(this.introducerRepo);
	protected ConnectionCheckerProxy<M> connectionCheckerProxy = new ConnectionCheckerProxy<M>(this.connectionCheckerRepo);
	protected ValidatorProxy<M> validatorProxy = new ValidatorProxy<M>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<M>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<M>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<M>) rule);
		return this;
	}

	@Override
	public RuleContext<M> registerIntroducerRule(Introducer<M> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M> registerConnectionCheckerRule(ConnectionChecker<M> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M> registerValidatorRule(Validator<M> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public IntroducerRepository<M> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public ConnectionCheckerRepository<M> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<M> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public IntroducerProxy<M> getIntroducerProxy() {
		return this.introducerProxy;
	}

	@Override
	public ConnectionCheckerProxy<M> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<M> getValidatorProxy() {
		return this.validatorProxy;
	}
}
