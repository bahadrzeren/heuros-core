package org.heuros.core.rule.context;

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

public interface RuleContext<M> {
	public RuleContext<M> registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public RuleContext<M> registerIntroducerRule(Introducer<M> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M> registerConnectionCheckerRule(ConnectionChecker<M> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M> registerValidatorRule(Validator<M> rule) throws RuleAnnotationIsMissing;

	public IntroducerRepository<M> getIntroducerRepo();
	public ConnectionCheckerRepository<M> getConnectionCheckerRepo();
	public ValidatorRepository<M> getValidatorRepo();

	public IntroducerProxy<M> getIntroducerProxy();
	public ConnectionCheckerProxy<M> getConnectionCheckerProxy();
	public ValidatorProxy<M> getValidatorProxy();
}
