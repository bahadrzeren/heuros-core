package org.heuros.core;

import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.api.base.IModel;
import org.heuros.api.loader.Loader;

/**
 * Class that orchestrates related objects to initialize, run and report optimization runs.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input class.
 * @param <O> Type of the output class.
 */
public class Director<I extends IModel, O extends IModel> {

	private static Logger logger = Logger.getLogger(Director.class);

	private Loader<I> loader = null;

	private Director(Builder<I, O> builder) {
		this.loader = builder.loader;
	}

	public static class Builder<I extends IModel, O extends IModel> {

		private Loader<I> loader = null;

		public Builder() {
		}

		public Builder<I, O> setLoader(Loader<I> loader) {
			this.loader = loader;
			return this;
		}
		public Director<I, O> build() {
			return new Director<I, O>(this);
		}
	}

	public void optimize() {
		if (this.loader != null) {
			List<I> inputData = this.loader.extractData();
			if (inputData != null)
				inputData.forEach(logger::info);
		} else
			logger.error("Null loader!");
	}
}
