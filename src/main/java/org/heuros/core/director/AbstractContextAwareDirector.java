package org.heuros.core.director;

import org.apache.log4j.Logger;
import org.heuros.core.base.DataContext;
import org.heuros.core.base.RuleContext;
import org.heuros.core.modelbase.IModel;

/**
 * Abstract class with minimum ContextAwareDirector<I, O> implementation.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public abstract class AbstractContextAwareDirector<I extends IModel, O extends IModel>
						extends AbstractDirector<I, O>
						implements ContextAwareDirector<I, O> {

	private static Logger logger = Logger.getLogger(AbstractContextAwareDirector.class);

	protected RuleContext ruleContext = null;
	protected DataContext<I, O> dataContext = null;

	@Override
	public Director<I, O> registerRuleContext(RuleContext ruleContext) {
		this.ruleContext = ruleContext;
		return this;
	}

	@Override
	public Director<I, O> registerDataContext(DataContext<I, O> dataContext) {
		this.dataContext = dataContext;
		return this;
	}

	@Override
	public boolean check() {
		if (super.check()) {
			if (this.ruleContext == null)
				logger.error("Null ruleContext!");
			if (this.dataContext == null)
				logger.error("Null dataContext!");
			else
				return true;
		}
		return false;
	}
}
