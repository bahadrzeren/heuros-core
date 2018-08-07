package org.heuros.core.data.context.repo;

import java.util.ArrayList;
import java.util.List;

public class AbstractDataRepository<W> implements DataRepository<W> {
	protected List<W> list = new ArrayList<W>();

}
