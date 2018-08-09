package org.heuros.core.data.base;

public abstract class AbstractWrapper<M extends Model> implements Wrapper<M> {

	protected M wrappee;

	public AbstractWrapper(M wrappee) {
		this.wrappee = wrappee;
	}

	@Override
	public M getWrappee() {
		return wrappee;
	}

}
