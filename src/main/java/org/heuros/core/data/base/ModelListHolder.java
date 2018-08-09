package org.heuros.core.data.base;

public interface ModelListHolder<C extends Model> {
	public void addToTheFront(C c);
	public void addToTheEnd(C c);
	public C removeFirst();
	public C removeLast();
}
