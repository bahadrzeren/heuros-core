package org.heuros.core.rule;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.proxy.IntroducerProxy;
import org.heuros.core.rule.repo.IntroducerRepository;
import org.heuros.exception.RuleAnnotationIsMissing;

public interface IntroducerRuleContext<M extends Model> {
	public int registerIntroducerRule(Introducer<M> rule) throws RuleAnnotationIsMissing;
	public IntroducerRepository<M> getIntroducerRepo();
	public IntroducerProxy<M> getIntroducerProxy();
}
