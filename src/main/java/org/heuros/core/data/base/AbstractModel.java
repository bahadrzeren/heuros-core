package org.heuros.core.data.base;

/**
 * Abstract implementation of Model interface.
 * 
 * @author bahadrzeren
 * 
 * @see Model
 */
public abstract class AbstractModel implements Model {

	protected int ndx = -1;

	@Override
	public int getNdx() {
		return this.ndx;
	}

	@Override
	public void setNdx(int ndx) {
		this.ndx = ndx;
	}

	@Override
	public boolean isRegistered() {
		return ndx >= 0;
	}
}
