package org.heuros.core.rule;

import org.heuros.core.rule.inf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

public abstract class AbstractRuleContext implements RuleContext {

	public RuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (((Rule) rule).getAnnotation() == null)
			throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
		return this;
	}
}
