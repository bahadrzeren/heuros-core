package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.Leg;

public class LegRepository extends AbstractDataRepository<Leg>{

	public LegRepository(WrapperFactory<Leg> wrapperFactory) {
		super(wrapperFactory);
	}
}
