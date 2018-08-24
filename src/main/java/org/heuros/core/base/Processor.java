package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

public interface Processor<I extends View, O extends Model> {
	public List<O> proceed();
}
