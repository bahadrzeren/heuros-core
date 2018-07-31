package org.heuros.core.rule;

import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;

public interface RuleMinimum<M> extends Introducer<M>
										, ConnectionChecker<M> {
}
