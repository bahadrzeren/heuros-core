package org.heuros.pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.heuros.core.base.RuleContext;
import org.heuros.core.rule.inf.Rule;
import org.heuros.core.rule.repo.RuleRepo;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRepoIsMissing;

public class PairingRuleContext implements RuleContext {

	private List<RuleRepo<Rule, ?>> repos = new ArrayList<RuleRepo<Rule, ?>>();

//	private List<RuleRepo<Introducer<?>, ?>> introducerRepos = new ArrayList<RuleRepo<Introducer<?>, ?>>();

//	private RuleRepo<Introducer<Leg>> legRuleIntroducerRepo;
//	private RuleRepo<ConnectionChecker<Leg>> legRuleConnectionCheckRepo;
//
//	private RuleRepo<Introducer<Duty>> dutyRuleIntroducerRepo;
//	private RuleRepo<ConnectionChecker<Duty>> dutyRuleConnectionCheckRepo;
//	private RuleRepo<ExtensibilityChecker<Duty, Leg>> dutyExtensibilityCheckerRepo; 
//	private RuleRepo<Validator<Duty>> dutyRuleValidatorRepo;
//
//	private RuleRepo<Introducer<Pair>> pairRuleIntroducerRepo;
//	private RuleRepo<ExtensibilityChecker<Pair, Duty>> pairExtensibilityCheckerRepo; 
//	private RuleRepo<Validator<Pair>> pairRuleValidatorRepo;

	public PairingRuleContext() {
//		this.legRuleIntroducerRepo = new RuleRepository<Introducer<Leg>>();
//		this.legRuleConnectionCheckRepo = new RuleRepository<ConnectionChecker<Leg>>();
//
//		this.dutyRuleIntroducerRepo = new RuleRepository<Introducer<Duty>>();
//		this.dutyRuleConnectionCheckRepo = new RuleRepository<ConnectionChecker<Duty>>();
//		this.dutyExtensibilityCheckerRepo = new RuleRepository<ExtensibilityChecker<Duty, Leg>>();
//		this.dutyRuleValidatorRepo = new RuleRepository<Validator<Duty>>();
//
//		this.pairRuleIntroducerRepo = new RuleRepository<Introducer<Pair>>();
//		this.pairExtensibilityCheckerRepo = new RuleRepository<ExtensibilityChecker<Pair, Duty>>();
//		this.pairRuleValidatorRepo = new RuleRepository<Validator<Pair>>();
	}

	public RuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing, RuleRepoIsMissing {
		if (rule.getAnnotation() == null)
			throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
		Optional<RuleRepo<Rule, ?>> repoOpt =
						repos.stream().filter((repo) ->
													(rule.isImplemented(repo.getRuleInterfaceType()))
													&& (repo.getModelType() == 
														rule.getAnnotation().modelType()))
															.findFirst();
		if (repoOpt.isPresent())
			repoOpt.get().registerRule(rule);
		else
			throw new RuleRepoIsMissing("Repository for the rule implementation could not be found!");
		return this;
	}
}
