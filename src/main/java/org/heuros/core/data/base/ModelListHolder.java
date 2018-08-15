package org.heuros.core.data.base;

public interface ModelListHolder<C extends Model> {
	public ModelListHolder<C> addToTheEnd(C c);
	public C removeLast();
}
