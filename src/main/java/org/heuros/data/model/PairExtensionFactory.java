package org.heuros.data.model;

import org.heuros.core.data.base.ExtensionFactory;

public class PairExtensionFactory implements ExtensionFactory<PairExtension> {

	@Override
	public PairExtension createExtension() {
		return new PairExt();
	}

}
