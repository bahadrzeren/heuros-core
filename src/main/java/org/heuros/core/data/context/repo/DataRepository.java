package org.heuros.core.data.context.repo;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.ExtensionFactory;

public interface DataRepository<M, E> {
	public DataRepository<M, E> registerExtensionFactory(ExtensionFactory<E> extensionFactory);
	public Extension<M, E> registerModel(M m, E e);
}
