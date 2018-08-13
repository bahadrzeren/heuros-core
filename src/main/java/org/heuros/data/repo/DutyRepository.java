package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.DutyModel;

public class DutyRepository extends AbstractDataRepository<DutyModel> {

	public DutyRepository(WrapperFactory<DutyModel> wrapperFactory) {
		super(wrapperFactory);
	}
}
