package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.core.rule.inf.Rule;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface RuleRepo<R extends Rule, M> {
	public void registerRule(R rule) throws RuleAnnotationIsMissing;
	public List<R> getRules();
	public Class<?> getRuleInterfaceType();
	public Class<M> getModelType();
}
