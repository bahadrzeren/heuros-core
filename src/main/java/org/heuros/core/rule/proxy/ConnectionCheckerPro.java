package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ConnectionChecker;

/**
 * Rule interface used to check whether two child instances could be connected to each other.
 * It is implemented for cases like checking whether a leg could follow an other leg in a duty.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that those connectability is checked.
 */
public interface ConnectionCheckerPro<M extends View> extends ConnectionChecker<M> {
	public int areConnectable(M prevModel, M nextModel);
}
