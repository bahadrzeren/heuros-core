package org.heuros.core.director;

import org.heuros.core.base.DataContext;
import org.heuros.core.base.RuleContext;
import org.heuros.core.modelbase.IModel;

/**
 * An extension interface that uses rule and data contexts for processing/reporting the data.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public interface ContextAwareDirector<I extends IModel, O extends IModel> extends Director<I, O> {
	public Director<I, O> registerRuleContext(RuleContext ruleContext);
	public Director<I, O> registerDataContext(DataContext<I, O> dataContext);
}
