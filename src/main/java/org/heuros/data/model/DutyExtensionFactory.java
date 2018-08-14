package org.heuros.data.model;

import org.heuros.core.data.base.ExtensionFactory;

public class DutyExtensionFactory implements ExtensionFactory<DutyExtension>{

	@Override
	public DutyExtension createExtension() {
		return new DutyExt();
	}

}
