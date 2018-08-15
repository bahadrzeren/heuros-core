package org.heuros.core.data.base;

public abstract class AbstractModel implements Model {

	protected int ndx = -1;

	@Override
	public int getNdx() {
		return this.ndx;
	}

}
