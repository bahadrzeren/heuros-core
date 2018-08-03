package org.heuros.core.rule.context;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Validator;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRepoIsMissing;

public interface RuleContext<M> {
//	public RuleContext<M> registerIntroducerRepo(IntroducerRepository<M> ruleRepository);
	public RuleContext<M> registerIntroducerRule(Introducer<M> rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing;
//	public RuleContext<M> registerConnectionCheckerRepo(ConnectionCheckerRepository<M> ruleRepository);
	public RuleContext<M> registerConnectionCheckerRule(ConnectionChecker<M> rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing;
//	public <C> RuleContext<M> registerExtensibilityCheckerRepo(ExtensibilityCheckerRepository<M, C> ruleRepository);
	public <C> RuleContext<M> registerExtensibilityCheckerRule(ExtensibilityChecker<M, C> rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing;
//	public RuleContext<M> registerValidatorRepo(ValidatorRepository<M> ruleRepository);
	public RuleContext<M> registerValidatorRule(Validator<M> rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing;
}
