package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleRepository<R, M> {
	public void registerRule(R rule) throws RuleAnnotationIsMissing;
	public List<R> getRules();
}
