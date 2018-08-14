package org.heuros.data.model;

import java.util.List;

public class Pair implements PairModel {
	
	private List<Duty> duties;

	public List<Duty> getDuties() {
		return duties;
	}

	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}
}
