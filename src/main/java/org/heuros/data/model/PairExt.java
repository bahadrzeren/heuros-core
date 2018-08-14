package org.heuros.data.model;

public class PairExt implements PairExtension {

	private int blockTimeInMins = 0;

	@Override
	public int getBlockTimeInMins() {
		return this.blockTimeInMins;
	}
}
