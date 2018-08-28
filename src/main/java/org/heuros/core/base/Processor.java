package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

/**
 * Generic interface for the input processors.
 * 
 * @author bahadrzeren
 *
 * @param <I> Type of the input instances.
 * @param <O> Type of the output instances.
 */
public interface Processor<I extends View, O extends Model> {
	public List<O> proceed();
}
