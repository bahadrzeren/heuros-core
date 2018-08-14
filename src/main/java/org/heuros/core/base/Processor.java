package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

public interface Processor<I, 
							O extends Wrapper<M, E>, 
							M extends Model, 
							E extends Extension> {
	public List<O> proceed(List<I> input);
}
