package org.heuros.director;

import java.util.List;

import org.heuros.core.director.AbstractDirector;
import org.heuros.core.modelbase.IModel;

/**
 * Implementation to use for simple data format transformation purposes.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the model that will be subject to format change.
 */
public class DataTransformDirector<I extends IModel> extends AbstractDirector<I, I> {

	@Override
	public void proceed() {
		if (this.check()) {
			List<I> inputData = this.loader.extractData();
			this.reporter.reportData(inputData);
		}
	}
}
