package org.heuros.core.rule;

import org.heuros.core.rule.inf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleContext {
	public RuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing;
}
