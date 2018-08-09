package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.Model;

public interface Pair extends Model {
	public List<DutyImpl> getDuties();
}
