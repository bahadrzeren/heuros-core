package org.heuros.core.data.context.repo;

import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.data.base.WrapperFactory;

public abstract class AbstractDataRepository<M extends Model, E extends Extension> 
														implements DataRepository<M, E> {

	protected List<Wrapper<M, E>> list = new ArrayList<Wrapper<M, E>>();

	protected WrapperFactory<M, E> wrapperFactory;

	public AbstractDataRepository(WrapperFactory<M, E> wrapperFactory) {
		this.wrapperFactory = wrapperFactory;
	}

	@Override
	public int addToRepo(M m) {
		Wrapper<M, E> w = this.wrapperFactory.createWrapper(m);
		this.list.add(w);
		return this.list.size() - 1;
	}

	@Override
	public int addWrapperToRepo(Wrapper<M, E> w) {
		this.list.add(w);
		return this.list.size() - 1;
	}

	@Override
	public Wrapper<M, E> getWrapper(int ndx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public M getModel(int ndx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getExtension(int ndx) {
		// TODO Auto-generated method stub
		return null;
	}
}
