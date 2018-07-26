package org.heuros.core.director;

import org.heuros.core.base.Optimizer;
import org.heuros.core.modelbase.IModel;

/**
 * Extension interface that runs optimization processes.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public interface OptimizationDirector<I extends IModel, O extends IModel> extends ContextAwareDirector<I, O> {
	public Director<I, O> registerOptimizer(Optimizer<I, O> optimizer);
}
