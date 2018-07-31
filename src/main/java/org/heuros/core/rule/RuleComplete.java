package org.heuros.core.rule;

import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Validator;

public interface RuleComplete<M, C> extends RuleMinimum<M>
										, ExtensibilityChecker<M, C>
										, Validator<M> {
}
