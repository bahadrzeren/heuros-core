package org.heuros.core.data.base;

/**
 * Abstract implementation of the ModelFactory<M>.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model. Airport, Leg, Duty etc..
 */
public abstract class AbstractModelFactory<M extends Model> implements ModelFactory<M> {
}
