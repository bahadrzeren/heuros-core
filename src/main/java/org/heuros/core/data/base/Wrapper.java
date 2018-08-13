package org.heuros.core.data.base;

public interface Wrapper<M extends Model, E extends Extension> {
	public E getExtension();
	public M getWrappee();
}
