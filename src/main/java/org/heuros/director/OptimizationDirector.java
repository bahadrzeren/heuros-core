package org.heuros.director;

import java.util.List;

import org.heuros.core.director.AbstractDirector;

/**
 * Implementation to use for optimization runs.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the optimized model.
 */
public class OptimizationDirector<I, O>
				extends AbstractDirector<I, O> {

	@Override
	public void proceed() {
		if (this.check()) {
			List<I> inputData = this.loader.extractData();
			List<O> optimizedData = this.processor.startProcess();
			this.reporter.reportData(optimizedData);
		}
	}
}
