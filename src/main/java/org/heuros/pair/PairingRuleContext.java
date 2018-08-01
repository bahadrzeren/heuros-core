package org.heuros.pair;

import org.heuros.core.model.Duty;
import org.heuros.core.model.Leg;
import org.heuros.core.model.Pair;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Validator;
import org.heuros.core.rule.repo.RuleRepo;

public class PairingRuleContext {

	private RuleRepo<Introducer<Leg>> legRuleIntroducerRepo;
	private RuleRepo<ConnectionChecker<Leg>> legRuleConnectionCheckRepo;

	private RuleRepo<Introducer<Duty>> dutyRuleIntroducerRepo;
	private RuleRepo<ConnectionChecker<Duty>> dutyRuleConnectionCheckRepo;
	private RuleRepo<ExtensibilityChecker<Duty, Leg>> dutyExtensibilityCheckerRepo; 
	private RuleRepo<Validator<Duty>> dutyRuleValidatorRepo;

	private RuleRepo<Introducer<Pair>> pairRuleIntroducerRepo;
	private RuleRepo<ConnectionChecker<Pair>> pairRuleConnectionCheckRepo;
	private RuleRepo<ExtensibilityChecker<Pair, Duty>> pairExtensibilityCheckerRepo; 
	private RuleRepo<Validator<Pair>> pairRuleValidatorRepo;

	public PairingRuleContext() {
	}
}
