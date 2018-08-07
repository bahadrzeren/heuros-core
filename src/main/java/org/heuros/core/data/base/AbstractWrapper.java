package org.heuros.core.data.base;

/**
 * Implementation of the Wrapper interface.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model to be wrapped.
 * @param <E> Type of the extension class to be wrapped.
 */
public abstract class AbstractWrapper<M, E> implements Extension<M, E> {
	protected int ndx = -1;
	protected M wrappee = null;
	protected E extension = null;

	public int getNdx() {
		return ndx;
	}

	public M getWrappee() {
		return this.wrappee;
	}

	public E getExtension() {
		return this.extension;
	}
}
