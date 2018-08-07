package org.heuros.core.base;

import java.util.List;

/**
 * Generic interface for the input data loaders.
 * 
 * @author bahadrzeren
 *
 * @param <M> Class type of the information that will be extracted from the resource.
 */
public interface Loader<M> {
	public List<M> extractData();
}
