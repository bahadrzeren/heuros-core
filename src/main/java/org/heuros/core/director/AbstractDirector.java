package org.heuros.core.director;

import org.apache.log4j.Logger;
import org.heuros.core.base.Loader;
import org.heuros.core.base.Reporter;
import org.heuros.core.modelbase.IModel;

/**
 * Abstract class with minimum Director<I extends IModel, O extends IModel> implementation.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public abstract class AbstractDirector<I extends IModel, O extends IModel> implements Director<I, O> {

	private static Logger logger = Logger.getLogger(AbstractDirector.class);

	protected Loader<I> loader = null;
	protected Reporter<O> reporter = null;

	@Override
	public Director<I, O> registerLoader(Loader<I> loader) {
		this.loader = loader;
		return this;
	}

	@Override
	public Director<I, O> registerReporter(Reporter<O> reporter) {
		this.reporter = reporter;
		return this;
	}

	@Override
	public boolean check() {
		if (this.loader == null)
			logger.error("Null loader!");
		if (this.reporter == null)
			logger.error("Null reporter!");
		else
			return true;
		return false;
	}
}
