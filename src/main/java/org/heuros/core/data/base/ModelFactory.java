package org.heuros.core.data.base;

public interface ModelFactory<M extends Model> {
	public M generateModel();
}
