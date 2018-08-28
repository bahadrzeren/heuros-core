package org.heuros.core.data.repo;

import java.util.List;

import org.heuros.core.data.base.Model;

/**
 * Base interface for the model repositories.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model. Airport, Leg, Duty etc..
 */
public interface DataRepository<M extends Model> {
	public int addToRepo(M m);
	public M getModel(int ndx);
	public List<M> getModels();
}
