package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.LegModel;

public class LegRepository extends AbstractDataRepository<LegModel>{

	public LegRepository(WrapperFactory<LegModel> wrapperFactory) {
		super(wrapperFactory);
	}
}
