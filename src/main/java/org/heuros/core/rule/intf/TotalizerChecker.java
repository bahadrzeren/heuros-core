package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

/**
 * Rule interface used to check whether an model instance is valid.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances to be validated.
 */
public interface TotalizerChecker<M extends View> extends Rule {
	public boolean acceptable(int hbNdx, M m);
}
