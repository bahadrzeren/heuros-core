package org.heuros.core.data.context.repo;

import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public class AbstractDataRepository<M extends Model> implements DataRepository<M> {
	protected List<Wrapper<M>> list = new ArrayList<Wrapper<M>>();

	@Override
	public int addToRepo(M m) {
		// TODO Auto-generated method stub
		return this.list.size() - 1;
	}
}
