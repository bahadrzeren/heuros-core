package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.Pair;

public class PairRepository extends AbstractDataRepository<Pair> {

	public PairRepository(WrapperFactory<Pair> wrapperFactory) {
		super(wrapperFactory);
	}
}
