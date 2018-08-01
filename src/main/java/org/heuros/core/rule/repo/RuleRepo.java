package org.heuros.core.rule.repo;

import java.util.List;

import org.heuros.core.rule.inf.RuleImpl;

public interface RuleRepo<R> {
	public void registerRule(R rule) throws Exception;
	public List<R> getRules();
	public RuleImpl getRuleMeta();
}
