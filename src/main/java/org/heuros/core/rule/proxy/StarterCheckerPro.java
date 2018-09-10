package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.StarterChecker;

/**
 * Rule interface used to check whether a child instance would take the starting position in its parent.
 * It is implemented for cases like checking whether a particular leg could be the fist leg in a duty instance.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent class.
 * @param <C> Type of the child class.
 */
public interface StarterCheckerPro<P extends View, C extends View> extends StarterChecker<P, C> {
	public int canBeStarter(C model);
}
