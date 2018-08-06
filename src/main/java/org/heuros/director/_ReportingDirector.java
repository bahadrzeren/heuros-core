package org.heuros.director;

import java.util.List;

import org.heuros.core.director.AbstractDirector;

/**
 * Implementation to use for loading different datasets/solutions for reporting purposes.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the model that will be subject to report calculations.
 */
public class _ReportingDirector<I> extends AbstractDirector<I, I> {

	@Override
	public void proceed() {
		if (this.check()) {
			List<I> inputData = this.loader.extractData();
			this.reporter.reportData(inputData);
		}
	}
}
