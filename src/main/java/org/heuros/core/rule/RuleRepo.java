package org.heuros.core.rule;

import java.util.List;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.RuleImpl;
import org.heuros.core.rule.inf.Validator;

public interface RuleRepo<M, C> {
	public void registerIntroducer(Introducer<M> introducer) throws Exception;
	public void registerConnectionChecker(ConnectionChecker<M> connectionChecker) throws Exception;
	public void registerExtensibilityChecker(ExtensibilityChecker<M, C> extensibilityChecker) throws Exception;
	public void registerValidator(Validator<M> validator) throws Exception;

	public List<Introducer<M>> getIntroducers();
	public List<ConnectionChecker<M>> getConnectionCheckers();
	public List<ExtensibilityChecker<M, C>> getExtensibilityCheckers();
	public List<Validator<M>> getValidators();

	public RuleImpl getIntroducerMeta(Introducer<M> introducer);
	public RuleImpl getConnectionCheckerMeta(ConnectionChecker<M> connectionChecker);
	public RuleImpl getExtensibilityCheckerMeta(ExtensibilityChecker<M, C> extensibilityChecker);
	public RuleImpl getValidatorMeta(Validator<M> validator);
}
