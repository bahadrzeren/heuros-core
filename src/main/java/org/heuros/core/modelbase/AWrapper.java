package org.heuros.core.modelbase;

public abstract class AWrapper<W, E> {
	protected W wrappee = null;
	protected E extension = null;

	public AWrapper(W wrappee, E extension) {
		this.wrappee = wrappee;
		this.extension = extension;
	}

//	public void onGenerate(W wrappee);
//	public void onRegister(W wrappee, E extension);

	public W getWrappee() {
		return this.wrappee;
	}

	public E getExtension() {
		return this.extension;
	}
}
