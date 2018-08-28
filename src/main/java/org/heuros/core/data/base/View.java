package org.heuros.core.data.base;

/**
 * Base interface for the model classes that will be used in rule implementations.
 * Successors of this interface must not include methods that change contents of instance.
 * Only getters or alike methods are supposed to be added.
 * 
 * @author bahadrzeren
 *
 */
public interface View {
	/**
	 * Index number of the instance in the list of related repository.
	 * @return Index number of the instance.
	 */
	public int getNdx();
}
