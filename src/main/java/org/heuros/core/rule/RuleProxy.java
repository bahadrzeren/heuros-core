package org.heuros.core.rule;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.Validator;

public interface RuleProxy<T, P> extends Introducer<T>
											, ConnectionChecker<T>
											, ExtensibilityChecker<T, P>
											, Validator<T> {
}
