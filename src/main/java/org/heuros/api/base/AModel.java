package org.heuros.api.base;

/**
 * Root abstract class for all model objects that are stored in arrays/lists. 
 * 
 * @author bahadrzeren
 *
 */
public abstract class AModel implements IModel {

	protected int ndx;

	/**
	 * @see IModel.getNdx
	 */
	public int getNdx() {
		return ndx;
	}

	/**
	 * @see IModel.setNdx
	 */
	public void setNdx(int ndx){
		
	}
}
