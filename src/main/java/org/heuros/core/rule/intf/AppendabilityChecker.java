package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

/**
 * Rule interface used to check whether a parent instance can be extended by appending a particular child instance.
 * It is implemented for cases like checking whether a duty could be extended by appending a particular leg instance.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent class.
 * @param <C> Type of the child class.
 */
public interface AppendabilityChecker<P extends View, C extends View> extends Rule {
	public boolean isAppendable(int hbNdx, P p, C c);
}
