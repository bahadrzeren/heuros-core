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

public interface RuleContext<W extends Wrapper<M>, M extends Model> {
	public RuleContext<W, M> registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public RuleContext<W, M> registerIntroducerRule(Introducer<W, M> rule) throws RuleAnnotationIsMissing;
	public RuleContext<W, M> registerConnectionCheckerRule(ConnectionChecker<W, M> rule) throws RuleAnnotationIsMissing;
	public RuleContext<W, M> registerValidatorRule(Validator<W, M> rule) throws RuleAnnotationIsMissing;

	public IntroducerRepository<W, M> getIntroducerRepo();
	public ConnectionCheckerRepository<W, M> getConnectionCheckerRepo();
	public ValidatorRepository<W, M> getValidatorRepo();

	public IntroducerProxy<W, M> getIntroducerProxy();
	public ConnectionCheckerProxy<W, M> getConnectionCheckerProxy();
	public ValidatorProxy<W, M> getValidatorProxy();
}
