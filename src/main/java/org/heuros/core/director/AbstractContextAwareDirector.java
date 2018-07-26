package org.heuros.core.director;

import org.apache.log4j.Logger;
import org.heuros.core.base.RepoContext;
import org.heuros.core.base.RuleContext;
import org.heuros.core.modelbase.IModel;

/**
 * Abstract class with ContextAwareDirector<I extends IModel, O extends IModel> implementation.
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
	protected RepoContext<I, O> repoContext = null;

	@Override
	public Director<I, O> registerRuleContext(RuleContext ruleContext) {
		this.ruleContext = ruleContext;
		return this;
	}

	@Override
	public Director<I, O> registerRepoContext(RepoContext<I, O> repoContext) {
		this.repoContext = repoContext;
		return this;
	}

	@Override
	public boolean check() {
		if (super.check()) {
			if (this.ruleContext == null)
				logger.error("Null ruleContext!");
			if (this.repoContext == null)
				logger.error("Null repoContext!");
			else
				return true;
		}
		return false;
	}
}
