package org.heuros.core.data.context.repo;

import org.heuros.core.data.base.Model;

public interface DataRepository<M extends Model> {
	public int addToRepo(M m);
}