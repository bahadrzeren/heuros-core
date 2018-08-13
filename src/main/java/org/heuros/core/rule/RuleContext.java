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

public interface RuleContext<M extends Model, E extends Extension> {
	public RuleContext<M, E> registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, E> registerIntroducerRule(Introducer<M, E> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, E> registerConnectionCheckerRule(ConnectionChecker<M, E> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, E> registerValidatorRule(Validator<M, E> rule) throws RuleAnnotationIsMissing;

	public IntroducerRepository<M, E> getIntroducerRepo();
	public ConnectionCheckerRepository<M, E> getConnectionCheckerRepo();
	public ValidatorRepository<M, E> getValidatorRepo();

	public IntroducerProxy<M, E> getIntroducerProxy();
	public ConnectionCheckerProxy<M, E> getConnectionCheckerProxy();
	public ValidatorProxy<M, E> getValidatorProxy();
}
