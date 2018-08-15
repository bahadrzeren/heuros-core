package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
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

public abstract class AbstractRuleContext<M extends Model, V extends View> 
													implements RuleContext<M, V> {

	protected IntroducerRepository<M> introducerRepo = new IntroducerRepository<M>();
	protected ConnectionCheckerRepository<V> connectionCheckerRepo = new ConnectionCheckerRepository<V>();
	protected ValidatorRepository<V> validatorRepo = new ValidatorRepository<V>();

	protected IntroducerProxy<M> introducerProxy = new IntroducerProxy<M>(this.introducerRepo);
	protected ConnectionCheckerProxy<V> connectionCheckerProxy = new ConnectionCheckerProxy<V>(this.connectionCheckerRepo);
	protected ValidatorProxy<V> validatorProxy = new ValidatorProxy<V>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<M, V> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<M>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<V>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<V>) rule);
		return this;
	}

	@Override
	public RuleContext<M, V> registerIntroducerRule(Introducer<M> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M, V> registerConnectionCheckerRule(ConnectionChecker<V> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<M, V> registerValidatorRule(Validator<V> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public IntroducerRepository<M> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public ConnectionCheckerRepository<V> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<V> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public IntroducerProxy<M> getIntroducerProxy() {
		return this.introducerProxy;
	}

	@Override
	public ConnectionCheckerProxy<V> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<V> getValidatorProxy() {
		return this.validatorProxy;
	}
}
