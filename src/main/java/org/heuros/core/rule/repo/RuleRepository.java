package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleRepository<R> {
	public void registerRule(R rule) throws RuleAnnotationIsMissing;
	public void removeRule(R rule);
	public List<R> getRules();
}
