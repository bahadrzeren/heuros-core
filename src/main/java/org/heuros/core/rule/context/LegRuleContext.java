package org.heuros.core.rule.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.heuros.core.model.Leg;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.repo.ConnectionCheckerRepository;
import org.heuros.core.rule.repo.ExtensibilityCheckerRepository;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.core.rule.repo.ValidatorRepository;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRepoIsMissing;

public class LegRuleContext implements RuleContext<Leg> {

	private Logger logger = Logger.getLogger(LegRuleContext.class);

	private IntroducerRepository<Leg> introducerRepo = new IntroducerRepository<Leg>();
	private ConnectionCheckerRepository<Leg> connectionCheckRepo = null;
	private ValidatorRepository<Leg> validatorRepo = null;

	@Override
	public RuleContext<Leg> registerIntroducerRepo(IntroducerRepository<Leg> ruleRepository) {
		this.introducerRepo = ruleRepository;
		return this;
	}

	@Override
	public RuleContext<Leg> registerIntroducerRule(Introducer<Leg> rule)
			throws RuleAnnotationIsMissing, RuleRepoIsMissing {
		if (introducerRepo == null)
			throw new RuleRepoIsMissing("Repository for the rule implementation could not be found!");
		else
			introducerRepo.registerRule(rule);
		return this;
	}

	@Override
	public RuleContext<Leg> registerConnectionCheckerRepo(ConnectionCheckerRepository<Leg> ruleRepository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleContext<Leg> registerConnectionCheckerRule(ConnectionChecker<Leg> rule)
			throws RuleAnnotationIsMissing, RuleRepoIsMissing {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C> RuleContext<Leg> registerExtensibilityCheckerRepo(
			ExtensibilityCheckerRepository<Leg, C> ruleRepository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C> RuleContext<Leg> registerExtensibilityCheckerRule(ExtensibilityChecker<Leg, C> rule)
			throws RuleAnnotationIsMissing, RuleRepoIsMissing {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleContext<Leg> registerValidatorRepo(ValidatorRepository<Leg> ruleRepository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleContext<Leg> registerValidatorRule(Validator<Leg> rule)
			throws RuleAnnotationIsMissing, RuleRepoIsMissing {
		// TODO Auto-generated method stub
		return null;
	}
}