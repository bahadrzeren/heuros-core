package org.heuros.core.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImpl;
import org.heuros.core.rule.inf.Validator;

public class RuleRepository<T, P> implements RuleRepo<T, P> {

	private Logger logger = Logger.getLogger(RuleRepository.class);

	protected Map<Introducer<T>, RuleImpl> introducerMetas = new HashMap<Introducer<T>, RuleImpl>();
	protected Map<ConnectionChecker<T>, RuleImpl> connectionCheckerMetas = new HashMap<ConnectionChecker<T>, RuleImpl>();
	protected Map<ExtensibilityChecker<T, P>, RuleImpl> extensibilityCheckerMetas = new HashMap<ExtensibilityChecker<T, P>, RuleImpl>();
	protected Map<Validator<T>, RuleImpl> validatorMetas = new HashMap<Validator<T>, RuleImpl>();

	protected List<Introducer<T>> introducers = new ArrayList<Introducer<T>>();
	protected List<ConnectionChecker<T>> connectionCheckers = new ArrayList<ConnectionChecker<T>>();
	protected List<ExtensibilityChecker<T, P>> extensibilityCheckers = new ArrayList<ExtensibilityChecker<T, P>>();
	protected List<Validator<T>> validators = new ArrayList<Validator<T>>();

	private <I> void register(List<I> ruleList, Map<I, RuleImpl> metaMap, I ruleImpl) throws Exception {
		if (ruleList.stream().filter((i) -> i == ruleImpl)
				.findFirst()
				.isPresent())
			logger.error("Rule impl is already registered!");
		else {
			RuleImpl ruleMeta = ruleImpl.getClass().getAnnotation(RuleImpl.class);
			if (ruleMeta == null)
				throw new Exception("@Rule annotation could not be found!");
			metaMap.put(ruleImpl, ruleMeta);
			ruleList.add(ruleImpl);
		}
	}

	@Override
	public void registerIntroducer(Introducer<T> introducer) throws Exception {
//		if (this.introducers.stream().filter((i) -> i == introducer)
//										.findFirst()
//										.isPresent())
//			logger.error("Introducer is already registered!");
//		else {
//			Rule ruleMeta = introducer.getClass().getAnnotation(Rule.class);
//			if (ruleMeta == null)
//				throw new Exception("@Rule annotation could not be found!");
//			this.introducerMetas.put(introducer, ruleMeta);
//			this.introducers.add(introducer);
//		}
		this.register(this.introducers, this.introducerMetas, introducer);
	}

	@Override
	public void registerConnectionChecker(ConnectionChecker<T> connectionChecker) throws Exception {
//		if (this.connectionCheckers.stream().filter((i) -> i == connectionChecker)
//											.findFirst()
//											.isPresent())
//			logger.error("Connection checker is already registered!");
//		else {
//			Rule ruleMeta = connectionChecker.getClass().getAnnotation(Rule.class);
//			if (ruleMeta == null)
//				throw new Exception("@Rule annotation could not be found!");
//			this.connectionCheckerMetas.put(connectionChecker, ruleMeta);
//			this.connectionCheckers.add(connectionChecker);
//		}
		this.register(this.connectionCheckers, this.connectionCheckerMetas, connectionChecker);
	}

	@Override
	public void registerExtensibilityChecker(ExtensibilityChecker<T, P> extensibilityChecker) throws Exception {
//		if (this.extensibilityCheckers.stream().filter((i) -> i == extensibilityChecker)
//												.findFirst()
//												.isPresent())
//			logger.error("Extensibility checker is already registered!");
//		else {
//			Rule ruleMeta = extensibilityChecker.getClass().getAnnotation(Rule.class);
//			if (ruleMeta == null)
//				throw new Exception("@Rule annotation could not be found!");
//			this.extensibilityCheckerMetas.put(extensibilityChecker, ruleMeta);
//			this.extensibilityCheckers.add(extensibilityChecker);
//		}
		this.register(this.extensibilityCheckers, this.extensibilityCheckerMetas, extensibilityChecker);
	}

	@Override
	public void registerValidator(Validator<T> validator) throws Exception {
//		if (this.validators.stream().filter((i) -> i == validator)
//									.findFirst()
//									.isPresent())
//			logger.error("Validator is already registered!");
//		else {
//			Rule ruleMeta = validator.getClass().getAnnotation(Rule.class);
//			if (ruleMeta == null)
//				throw new Exception("@Rule annotation could not be found!");
//			this.validatorMetas.put(validator, ruleMeta);
//			this.validators.add(validator);
//		}
		this.register(this.validators, this.validatorMetas, validator);
	}

	public List<Introducer<T>> getIntroducers() {
		return this.introducers;
	}

	public List<ConnectionChecker<T>> getConnectionCheckers() {
		return this.connectionCheckers;
	}

	public List<ExtensibilityChecker<T, P>> getExtensibilityCheckers() {
		return this.extensibilityCheckers;
	}

	public List<Validator<T>> getValidators() {
		return this.validators;
	}

	public RuleImpl getIntroducerMeta(Introducer<T> introducer) {
		return this.introducerMetas.get(introducer);
	}

	public RuleImpl getConnectionCheckerMeta(ConnectionChecker<T> connectionChecker) {
		return this.connectionCheckerMetas.get(connectionChecker);
	}

	public RuleImpl getExtensibilityCheckerMeta(ExtensibilityChecker<T, P> extensibilityChecker) {
		return this.extensibilityCheckerMetas.get(extensibilityChecker);
	}

	public RuleImpl getValidatorMeta(Validator<T> validator) {
		return this.validatorMetas.get(validator);
	}
}
