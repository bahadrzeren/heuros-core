package org.heuros.core.director;

import org.heuros.core.base.Loader;
import org.heuros.core.base.Processor;
import org.heuros.core.base.Reporter;

/**
 * The root interface that uses Loader and Reporter implementations to perform basic operations that constitute just loading input data of a generic type <I> and reporting the final data as a generic type <O>.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input model.
 * @param <O> Type of the output model.
 */
public interface Director<I, O> {
	public Director<I, O> registerLoader(Loader<I> loader);
	public Director<I, O> registerProcessor(Processor<O> processor);
	public Director<I, O> registerReporter(Reporter<O> reporter);
	public boolean check();
	public void proceed();
}
