package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.Model;

public class Pair implements Model, PairView {
	
	private List<Duty> duties;

	public List<Duty> getDuties() {
		return duties;
	}

	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}
}
