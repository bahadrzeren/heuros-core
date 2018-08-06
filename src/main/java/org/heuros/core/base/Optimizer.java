package org.heuros.core.base;

import java.util.List;

public interface Optimizer<I, O> {
	public List<O> optimize();
}
