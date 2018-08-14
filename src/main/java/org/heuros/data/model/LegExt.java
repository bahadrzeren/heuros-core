package org.heuros.data.model;

public class LegExt implements LegExtension {

	private int blockTimeInMins = 0;

	@Override
	public int getBlockTimeInMins() {
		return this.blockTimeInMins;
	}

}
