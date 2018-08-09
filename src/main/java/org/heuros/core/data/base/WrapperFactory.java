package org.heuros.core.data.base;

public interface WrapperFactory<M extends Model> {
	public Wrapper<M> createWrapper(M m);
}
