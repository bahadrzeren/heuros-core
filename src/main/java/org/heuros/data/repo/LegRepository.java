package org.heuros.data.repo;

import org.heuros.core.data.base.ExtensionFactory;
import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;

public class LegRepository extends AbstractDataRepository<LegModel, LegExtension>{

	public LegRepository(WrapperFactory<LegModel, LegExtension> wrapperFactory,
							ExtensionFactory<LegExtension> extensionFactory) {
		super(wrapperFactory, extensionFactory);
	}
}
