package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Model;

public interface Processor<IM extends Model, OM extends Model> {
	public List<OM> proceed(List<IM> input);
}
