package org.heuros.core.director;

import org.apache.log4j.Logger;
import org.heuros.core.base.Optimizer;

/**
 * Partial implementation of the interface OptimizationDirector<I, O>.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public abstract class AbstractOptimizationDirector<I, O>
						extends AbstractDirector<I, O>
						implements OptimizationDirector<I, O>{

	private static Logger logger = Logger.getLogger(AbstractOptimizationDirector.class);

	protected Optimizer<I, O> optimizer = null;

	@Override
	public Director<I, O> registerOptimizer(Optimizer<I, O> optimizer) {
		this.optimizer = optimizer;
		return this;
	}

	@Override
	public boolean check() {
		if (super.check()) {
			if (this.optimizer == null)
				logger.error("Null optimizer!");
			else
				return true;
		}
		return false;
	}
}
