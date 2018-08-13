package org.heuros.core.data.base;

public interface WrapperFactory<M extends Model, E extends Extension> {
	public Wrapper<M, E> createWrapper(M m);
}
