package org.heuros.core.rule;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Validator;

public interface RuleRepository<T, P> {
	public void registerIntroducer(Introducer<T> introducer) throws Exception;
	public void registerConnectionChecker(ConnectionChecker<T> connectionChecker) throws Exception;
	public void registerExtensibilityChecker(ExtensibilityChecker<T, P> extensibilityChecker) throws Exception;
	public void registerValidator(Validator<T> validator) throws Exception;
}
