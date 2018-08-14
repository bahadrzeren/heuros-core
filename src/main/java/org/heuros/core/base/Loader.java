package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Model;

/**
 * Generic interface for the input data loaders.
 * 
 * @author bahadrzeren
 *
 * @param <M> Class type of the information that will be extracted from the resource.
 */
public interface Loader<M extends Model> {
	public List<M> extractData();
}
