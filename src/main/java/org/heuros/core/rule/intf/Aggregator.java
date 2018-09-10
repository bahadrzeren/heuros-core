package org.heuros.core.rule.intf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

/**
 * Rule interface used to set aggregated rule parameters of parent model instances after adding or removing children from their content.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model. Duty, Pair etc..
 * @param <C> Type of the child model. Leg, Duty etc..
 */
public interface Aggregator<P extends Model, C extends View> extends Rule {
	/**
	 * Adds c to the p's content and calculates and incorporates c's contribution on p.
	 * 
	 * @param p Parent class instance.
	 * @param c Child class instance.
	 */
	public void append(P p, C c);
	/**
	 * Calculates and incorporates c's contribution on p without changing p's content.
	 * Mainly used for rule check without changing p's content.
	 * 
	 * @param p Parent class instance.
	 * @param c Child class instance.
	 */
	public void softAppend(P p, C c);
	/**
	 * Removes the last child from the p's content and calculates and removes c's contribution on p.
	 * 
	 * @param p Parent class instance.
	 */
	public C removeLast(P p);
	/**
	 * Resets parent class instance's aggregated parameters.
	 * 
	 * @param p Parent instance to reset aggregation parameters.
	 */
	public void reset(P p);
	/**
	 * Recalculates parent class instance's aggregated parameters.
	 * 
	 * @param p Parent instance to recalculate aggregation parameters.
	 */
	public void reCalculate(P p);
}
