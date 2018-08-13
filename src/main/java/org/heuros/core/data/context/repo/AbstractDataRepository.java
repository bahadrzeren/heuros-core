package org.heuros.core.data.context.repo;

import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.data.base.WrapperFactory;

public abstract class AbstractDataRepository<M extends Model> implements DataRepository<M> {

	protected List<Wrapper<M>> list = new ArrayList<Wrapper<M>>();

	protected WrapperFactory<M> wrapperFactory;

	public AbstractDataRepository(WrapperFactory<M> wrapperFactory) {
		this.wrapperFactory = wrapperFactory;
	}

	@Override
	public int addToRepo(M m) {
		Wrapper<M> w = this.wrapperFactory.createWrapper(m);
		this.list.add(w);
		return this.list.size() - 1;
	}
}
