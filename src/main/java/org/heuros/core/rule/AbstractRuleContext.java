package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
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

public abstract class AbstractRuleContext<W extends Wrapper<M>, M extends Model> implements RuleContext<W, M> {

	protected IntroducerRepository<W, M> introducerRepo = new IntroducerRepository<W, M>();
	protected ConnectionCheckerRepository<W, M> connectionCheckerRepo = new ConnectionCheckerRepository<W, M>();
	protected ValidatorRepository<W, M> validatorRepo = new ValidatorRepository<W, M>();

	protected IntroducerProxy<W, M> introducerProxy = new IntroducerProxy<W, M>(this.introducerRepo);
	protected ConnectionCheckerProxy<W, M> connectionCheckerProxy = new ConnectionCheckerProxy<W, M>(this.connectionCheckerRepo);
	protected ValidatorProxy<W, M> validatorProxy = new ValidatorProxy<W, M>(this.validatorRepo);

	@SuppressWarnings("unchecked")
	@Override
	public RuleContext<W, M> registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (rule.isImplemented(Introducer.class))
			this.registerIntroducerRule((Introducer<W, M>) rule);
		if (rule.isImplemented(ConnectionChecker.class))
			this.registerConnectionCheckerRule((ConnectionChecker<W, M>) rule);
		if (rule.isImplemented(Validator.class))
			this.registerValidatorRule((Validator<W, M>) rule);
		return this;
	}

	@Override
	public RuleContext<W, M> registerIntroducerRule(Introducer<W, M> rule)
			throws RuleAnnotationIsMissing {
		this.introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<W, M> registerConnectionCheckerRule(ConnectionChecker<W, M> rule)
			throws RuleAnnotationIsMissing {
		this.connectionCheckerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<W, M> registerValidatorRule(Validator<W, M> rule)
			throws RuleAnnotationIsMissing {
		this.validatorRepo.registerRule(rule);
		return this;
	}

	@Override
	public IntroducerRepository<W, M> getIntroducerRepo() {
		return this.introducerRepo;
	}

	@Override
	public ConnectionCheckerRepository<W, M> getConnectionCheckerRepo() {
		return this.connectionCheckerRepo;
	}

	@Override
	public ValidatorRepository<W, M> getValidatorRepo() {
		return this.validatorRepo;
	}

	@Override
	public IntroducerProxy<W, M> getIntroducerProxy() {
		return this.introducerProxy;
	}

	@Override
	public ConnectionCheckerProxy<W, M> getConnectionCheckerProxy() {
		return this.connectionCheckerProxy;
	}

	@Override
	public ValidatorProxy<W, M> getValidatorProxy() {
		return this.validatorProxy;
	}
}
