package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

/**
 * Rule interface used to check whether two child instances could be connected to each other.
 * It is implemented for cases like checking whether a leg could follow an other leg in a duty.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that those connectability is checked.
 */
public interface ConnectionChecker<M extends View> extends Rule {
	public boolean areConnectable(M prevModel, 
									M nextModel, 
									int hbNdx);
}
