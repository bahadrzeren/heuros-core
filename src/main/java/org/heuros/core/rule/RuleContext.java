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

public interface RuleContext<M extends Model, V extends View> {
	public RuleContext<M, V> registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, V> registerIntroducerRule(Introducer<M> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, V> registerConnectionCheckerRule(ConnectionChecker<V> rule) throws RuleAnnotationIsMissing;
	public RuleContext<M, V> registerValidatorRule(Validator<V> rule) throws RuleAnnotationIsMissing;

	public IntroducerRepository<M> getIntroducerRepo();
	public ConnectionCheckerRepository<V> getConnectionCheckerRepo();
	public ValidatorRepository<V> getValidatorRepo();

	public IntroducerProxy<M> getIntroducerProxy();
	public ConnectionCheckerProxy<V> getConnectionCheckerProxy();
	public ValidatorProxy<V> getValidatorProxy();
}
