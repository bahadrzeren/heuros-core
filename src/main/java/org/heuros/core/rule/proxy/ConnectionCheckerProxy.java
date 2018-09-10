package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.ConnectionChecker;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for ConnectionChecker rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the model instances that connectivityCheck will be applied to.
 * @see ConnectionChecker
 */
public class ConnectionCheckerProxy<V extends View> implements ConnectionCheckerPro<V> {

	private RuleRepository<ConnectionChecker<V>> repo = null;

	private int numOfBases = 0;

	public ConnectionCheckerProxy(RuleRepository<ConnectionChecker<V>> repo, int numOfBases) {
		this.repo = repo;
		this.numOfBases = numOfBases;
	}

	@Override
	public boolean areConnectable(V prevModel, V nextModel, int hbNdx) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).areConnectable(prevModel, nextModel, hbNdx))
				return false;
		return true;
	}

	@Override
	public int areConnectable(V prevModel, V nextModel) {
		int res = 0;
		for (int i = 0; i < this.numOfBases; i++)
			if (this.areConnectable(prevModel, nextModel, i))
				res |= (1 << i);
		return res;
	}
}
