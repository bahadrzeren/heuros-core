package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.PairExtension;
import org.heuros.data.model.PairModel;

public class PairRepository extends AbstractDataRepository<PairModel, PairExtension> {

	public PairRepository(WrapperFactory<PairModel, PairExtension> wrapperFactory) {
		super(wrapperFactory);
	}
}
