package org.heuros.core.entity.context.repo;

import java.util.List;

import org.heuros.core.data.base.Model;

public interface DataRepository<M extends Model> {
	public int addToRepo(M m);
	public M getModel(int ndx);
	public List<M> getModels();
}
