package org.heuros.core.data.base;

/**
 * Factory interface for model classes.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model. Airport, Leg, Duty etc..
 */
public interface ModelFactory<M extends Model> {
	public M generateModel();
}
