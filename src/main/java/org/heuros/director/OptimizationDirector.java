package org.heuros.director;

import java.util.List;

import org.heuros.core.director.AbstractOptimizationDirector;
import org.heuros.core.modelbase.IModel;

/**
 * Implementation to use for optimization runs.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the optimized model.
 */
public class OptimizationDirector<I extends IModel, O extends IModel>
				extends AbstractOptimizationDirector<I, O> {

	@Override
	public void proceed() {
		if (this.check()) {
			List<I> inputData = this.loader.extractData();
			this.dataContext.buildContext(inputData);
			List<O> optimizedData = this.optimizer.optimize(this.dataContext, this.ruleContext);
			this.reporter.reportData(optimizedData);
		}
	}
}
