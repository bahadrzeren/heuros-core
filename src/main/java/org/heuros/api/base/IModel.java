package org.heuros.api.base;

/**
 * Root interface for all model objects that are stored in arrays/lists/repos.
 * 
 * @author B_ZEREN
 *
 */
public interface IModel {
	/**
	 * Returns array index of the associated object.
	 * 
	 * @return current value of the index number of the associated object.
	 */
	public int getNdx();

	/**
	 * Sets array index of the associated object.
	 * 
	 * @param ndx new value of the index number of the associated object.
	 */
	public void setNdx(int ndx);
}
