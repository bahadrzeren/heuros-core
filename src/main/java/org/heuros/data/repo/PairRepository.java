package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.PairModel;

public class PairRepository extends AbstractDataRepository<PairModel> {

	public PairRepository(WrapperFactory<PairModel> wrapperFactory) {
		super(wrapperFactory);
	}
}
