package org.heuros.core.data.base;

public interface ModelListHolder<M extends Model> {
	public void addToTheFront(M m);
	public void addToTheEnd(M m);
	public M removeFirst();
	public M removeLast();
}
