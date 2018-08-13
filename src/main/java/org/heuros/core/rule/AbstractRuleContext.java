package org.heuros.core.rule;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
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

public abstract class AbstractRuleContext<M extends Model, E extends Extension> 
													implements RuleContext<M, E> {

	protected IntroducerRepository<M, E> introducerRepo = new IntroducerRepository<M, E>();
	protected ConnectionCheckerRepository<M, E> connectionCheckerRepo = new ConnectionCheckerRepository<M, E>();
	protected ValidatorRepository<M, E> validatorRepo = new ValidatorRepository<M, E>();

	protected IntroducerProxy<M, E> introducerProxy = new IntroducerProxy<M, E>(this.introducerRepo);
	protected ConnectionCheckerProxy<M, E> connectionCheckerProxy = new ConnectionCheckerProxy<M, E>(this.connectionCheckerRepo);
	protected ValidatorProxy<M, E> validatorProxy = new ValidatorProxy<M, E>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M, E> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<M, E>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<M, E>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<M, E>) rule);
		return this;
	}

	@Override
	public RuleContext<M, E> registerIntroducerRule(Introducer<M, E> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M, E> registerConnectionCheckerRule(ConnectionChecker<M, E> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M, E> registerValidatorRule(Validator<M, E> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public IntroducerRepository<M, E> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public ConnectionCheckerRepository<M, E> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<M, E> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public IntroducerProxy<M, E> getIntroducerProxy() {
		return this.introducerProxy;
	}

	@Override
	public ConnectionCheckerProxy<M, E> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<M, E> getValidatorProxy() {
		return this.validatorProxy;
	}
}
