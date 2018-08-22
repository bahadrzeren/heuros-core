package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleRepository<R> {
	public int registerRule(R rule) throws RuleAnnotationIsMissing;
	public int removeRule(R rule);
	public List<R> getRules();
}
