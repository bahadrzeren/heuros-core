package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

public interface Processor<I extends Model, O extends View> {
	public List<O> proceed();
}
