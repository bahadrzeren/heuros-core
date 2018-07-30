package org.heuros.core.modelbase;

/**
 * Wrapper interface that is used to encapsulate data model objects.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model to be wrapped.
 * @param <E> Type of the extension class to be wrapped.
 */
public interface Wrapper<M, E> {
	/**
	 * Returns array index of the associated object.
	 * 
	 * @return current value of the index number of the associated object.
	 */
	public int getNdx();

	/**
	 * Returns the object encapsulated.
	 * 
	 * @return The object encapsulated.
	 */
	public M getWrappee();

	/**
	 * Returns associated extension of the encapsulated object.
	 * 
	 * @return Associated extension of the encapsulated object.
	 */
	public E getExtension();
}
