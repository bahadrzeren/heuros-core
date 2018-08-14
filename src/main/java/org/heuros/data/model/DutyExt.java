package org.heuros.data.model;

public class DutyExt implements DutyExtension {

	private int blockTimeInMins = 0;

	@Override
	public int getBlockTimeInMins() {
		return this.blockTimeInMins;
	}

}
