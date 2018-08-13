package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.Model;

public interface PairModel extends Model {
	public List<Duty> getDuties();
}
