package org.heuros.core.data.base;

public interface ExtensionFactory<E extends Extension> {
	public E createExtension();
}
