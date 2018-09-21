package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.AppendabilityChecker;

/**
 * Rule interface used to check whether a parent instance can be extended by appending a particular child instance.
 * It is implemented for cases like checking whether a duty could be extended by appending a particular leg instance.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent class.
 * @param <C> Type of the child class.
 */
public interface AppendabilityCheckerPro<P extends View, C extends View> extends AppendabilityChecker<P, C> {
	public int isAppendable(P p, C c, boolean fw);
}
