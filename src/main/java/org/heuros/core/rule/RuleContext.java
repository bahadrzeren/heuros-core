package org.heuros.core.rule;

import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleContext {
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public int removeRule(Rule rule);
}
