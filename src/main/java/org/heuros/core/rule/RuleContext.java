package org.heuros.core.rule;

import org.heuros.core.rule.intf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Base interface for rule facade instances.
 * 
 * @author bahadrzeren
 *
 */
public interface RuleContext {
	public int registerRule(Rule rule) throws RuleAnnotationIsMissing;
	public int removeRule(Rule rule);
}
