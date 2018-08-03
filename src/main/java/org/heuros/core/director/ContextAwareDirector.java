package org.heuros.core.director;

import org.heuros.core.base.DataContext;
import org.heuros.core.rule.context.RuleContext;

/**
 * An extension of Director<I, O> interface which will be used for special implementations that need rule and data contexts.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public interface ContextAwareDirector<I, O> extends Director<I, O> {
	public Director<I, O> registerRuleContext(RuleContext ruleContext);
	public Director<I, O> registerDataContext(DataContext<I, O> dataContext);
}
