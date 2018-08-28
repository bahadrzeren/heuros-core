package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.exception.RuleAnnotationIsMissing;

/**
 * Root interface for rule implementation repositories.
 * 
 * @author bahadrzeren
 *
 * @param <R> Type of the rule interfaces those implementations will be stored. 
 */
public interface RuleRepository<R> {
	public int registerRule(R rule) throws RuleAnnotationIsMissing;
	public int removeRule(R rule);
	public List<R> getRules();
}
