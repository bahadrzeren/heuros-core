package org.heuros.core.rule.intf;

import org.heuros.core.data.base.Model;

/**
 * Rule interface used to set rule parameters of model instances after their generation.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances to be applied on.
 */
public interface Introducer<M extends Model> extends Rule {
	public boolean introduce(M m);
}
