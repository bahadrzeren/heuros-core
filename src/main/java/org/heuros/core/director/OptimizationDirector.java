package org.heuros.core.director;

import org.heuros.core.base.Optimizer;

/**
 * An extension interface which will be used for optimization implementations.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public interface OptimizationDirector<I, O> extends ContextAwareDirector<I, O> {
	public Director<I, O> registerOptimizer(Optimizer<I, O> optimizer);
}
