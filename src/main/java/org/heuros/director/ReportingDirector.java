package org.heuros.director;

import java.util.List;

import org.heuros.core.director.AbstractContextAwareDirector;
import org.heuros.core.modelbase.IModel;

/**
 * Implementation to use for loading different datasets/solutions for reporting purposes.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the model that will be subject to report calculations.
 */
public class ReportingDirector<I extends IModel> extends AbstractContextAwareDirector<I, I> {

	@Override
	public void proceed() {
		if (this.check()) {
			List<I> inputData = this.loader.extractData();
			this.dataContext.buildContext(inputData);
			this.reporter.reportData(inputData);
		}
	}
}
