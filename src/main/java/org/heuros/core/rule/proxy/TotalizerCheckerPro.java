package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.TotalizerChecker;

/**
 * Rule interface used to check whether an model instance is valid.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances to be validated.
 */
public interface TotalizerCheckerPro<M extends View> extends TotalizerChecker<M> {
	public int acceptable(M m);
}
