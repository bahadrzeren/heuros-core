package org.heuros.core.base;

import java.util.List;

public interface Processor<M> {
	public List<M> startProcess();
}
