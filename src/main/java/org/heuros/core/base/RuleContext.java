package org.heuros.core.base;

import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.repo.RuleRepository;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRepoIsMissing;

public interface RuleContext {
	public <M> RuleContext registerRepo(RuleRepository<Rule, M> ruleRepository);
	public RuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing;

//	public <M> RuleContext registerIntroducer(Introducer<M> rule) throws Exception;
//	public <M> RuleContext registerConnectionChecker(ConnectionChecker<M> rule) throws Exception;
//	public <M, C> RuleContext registerExtensibilityChecker(ExtensibilityChecker<M, C> rule) throws Exception;
//	public <M> RuleContext registerValidator(Validator<M> rule) throws Exception;
//
//	public <M> RuleRepo<Introducer<M>, M> getIntroducerRepo(Class<M> mc);
//	public <M> RuleRepo<ConnectionChecker<M>, M> getConnectionCheckerRepo(Class<M> mc);
//	public <M, C> RuleRepo<ExtensibilityChecker<M, C>, M> getExtensibilityCheckerRepo(Class<M> mc, Class<C> cc);
//	public <M> RuleRepo<Validator<M>, M> getValidatorRepo(Class<M> mc);
//
//	public <M> IntroducerProxy<M> getIntroducerProxy();
//	public <M> ConnectionCheckerProxy<M> getConnectionCheckerProxy();
//	public <M, C> ExtensibilityCheckerProxy<M, C> getExtensibilityCheckerProxy();
//	public <M> RuleContext getValidatorProxy(Validator<M> rule);
}
