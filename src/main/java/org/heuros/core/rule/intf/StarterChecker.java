package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

/**
 * Rule interface used to check whether a child instance would take the starting position in its parent.
 * It is implemented for cases like checking whether a particular leg could be the fist leg in a duty instance.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent class.
 * @param <C> Type of the child class.
 */
public interface StarterChecker<P extends View, C extends View> extends Rule {
	public boolean canBeStarter(int hbNdx, C model);
}
