package org.heuros.api.loader;

import java.util.List;

import org.heuros.api.base.IModel;

/**
 * Generic interface for the input data loaders.
 * 
 * @author bahadrzeren
 *
 * @param <T> Class type of the information that will be extracted.
 * 
 */
public interface Loader<T extends IModel> {
	public List<T> extractData();
}
