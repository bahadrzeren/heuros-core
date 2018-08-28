package org.heuros.core.data.repo;

import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.Model;

/**
 * Abstract implementation for the model repositories.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model. Airport, Leg, Duty etc..
 */
public abstract class AbstractDataRepository<M extends Model> implements DataRepository<M> {

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

	@Override
	public List<M> getModels() {
		return this.list;
	}
}
