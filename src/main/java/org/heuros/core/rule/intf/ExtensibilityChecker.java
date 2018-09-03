package org.heuros.core.rule.intf;

import org.heuros.core.data.base.View;

/**
 * Rule interface used to check whether a parent instance can be extended by appending more children instances.
 * It is implemented for cases like checking whether a duty could be extended by appending more legs.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instance that extensibility status will be checked.
 */
public interface ExtensibilityChecker<M extends View> extends Rule {
	public boolean isExtensible(M model, int hbNdx);
}
