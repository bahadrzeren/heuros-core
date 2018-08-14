package org.heuros.data.repo;

import org.heuros.core.data.base.WrapperFactory;
import org.heuros.core.data.context.repo.AbstractDataRepository;
import org.heuros.data.model.DutyExtension;
import org.heuros.data.model.DutyModel;

public class DutyRepository extends AbstractDataRepository<DutyModel, DutyExtension> {

	public DutyRepository(WrapperFactory<DutyModel, DutyExtension> wrapperFactory) {
		super(wrapperFactory);
	}
}
