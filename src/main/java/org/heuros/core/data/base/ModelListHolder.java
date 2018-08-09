package org.heuros.core.data.base;

public interface ModelListHolder<C extends Model> {
	public ModelListHolder<C> addToTheFront(C c);
	public ModelListHolder<C> addToTheEnd(C c);
	public C removeFirst();
	public C removeLast();
}
