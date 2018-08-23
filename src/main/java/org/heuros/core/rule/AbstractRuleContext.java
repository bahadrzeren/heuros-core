package org.heuros.core.rule;

import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.util.RuleUtil;

public abstract class AbstractRuleContext {

	public int registerRule(Rule rule) throws RuleAnnotationIsMissing {
		if (RuleUtil.ruleAnnotationGetter.getRuleImplementation(((Rule) rule)) == null)
			throw new RuleAnnotationIsMissing("@Rule annotation could not be found!");
		return 0;
	}

}
