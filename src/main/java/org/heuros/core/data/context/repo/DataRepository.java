package org.heuros.core.data.context.repo;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public interface DataRepository<M extends Model, E extends Extension> {
	public int addToRepo(M m);
	public Wrapper<M, E> getWrapper(int ndx);
	public M getModel(int ndx);
	public E getExtension(int ndx);
}
