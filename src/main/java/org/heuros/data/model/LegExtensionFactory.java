package org.heuros.data.model;

import org.heuros.core.data.base.ExtensionFactory;

public class LegExtensionFactory implements ExtensionFactory<LegExtension> {

	@Override
	public LegExtension createExtension() {
		return new LegExt();
	}

}
