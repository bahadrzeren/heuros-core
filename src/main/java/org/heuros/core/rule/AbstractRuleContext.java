package org.heuros.core.rule;

import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

public abstract class AbstractRuleContext implements RuleContext {

	public RuleContext registerRule(Rule rule) throws RuleAnnotationIsMissing {
//		if (((Rule) rule).getAnnotation() == null)
		if (RuleUtil.ruleAnnotationGetter.getRuleImplementation(((Rule) rule)) == null)
			throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
		return this;
	}

	public abstract void removeRule(Rule rule);
}
