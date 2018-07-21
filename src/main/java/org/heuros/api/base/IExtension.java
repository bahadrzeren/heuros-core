package org.heuros.api.base;

public interface IExtension<W> {
	public void onGenerate(W wrappee);
}
