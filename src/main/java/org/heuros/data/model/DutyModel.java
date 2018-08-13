package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.Model;

public interface DutyModel extends Model {
	public List<DutyLeg> getDutyLegs();
}
