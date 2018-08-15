package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Pair extends AbstractModel implements PairView {
	
	private List<Duty> duties;

	public List<Duty> getDuties() {
		return duties;
	}

	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}
}
