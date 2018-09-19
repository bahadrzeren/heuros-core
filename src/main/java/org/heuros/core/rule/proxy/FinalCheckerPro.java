package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.FinalChecker;

/**
 * Rule interface used to check whether an model instance is valid.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances to be validated.
 */
public interface FinalCheckerPro<M extends View> extends FinalChecker<M> {
	public int acceptable(M m);
}
