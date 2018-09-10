package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ExtensibilityChecker;

/**
 * Rule interface used to check whether a parent instance can be extended by appending more children instances.
 * It is implemented for cases like checking whether a duty could be extended by appending more legs.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instance that extensibility status will be checked.
 */
public interface ExtensibilityCheckerPro<M extends View> extends ExtensibilityChecker<M> {
	public int isExtensible(M model);
}
