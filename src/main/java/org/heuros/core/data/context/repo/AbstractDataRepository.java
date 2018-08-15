package org.heuros.core.data.context.repo;

import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.Model;

public abstract class AbstractDataRepository<M extends Model> 
														implements DataRepository<M> {

	protected List<M> list = new ArrayList<M>();

	@Override
	public int addToRepo(M m) {
		m.setNdx(this.list.size());
		this.list.add(m);
		return m.getNdx();
	}

	@Override
	public M getModel(int ndx) {
		return this.list.get(ndx);
	}
}
