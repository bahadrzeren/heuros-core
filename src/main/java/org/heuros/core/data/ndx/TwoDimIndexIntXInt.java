package org.heuros.core.data.ndx;

import org.heuros.core.data.base.View;

/**
 * Implementation of the two dimensional Integer and datetime index.
 * 
 * @author bahadrzeren
 *
 * @param <T> Type of the instances to be indexed.
 */
public class TwoDimIndexIntXInt<T extends View> extends TwoDimIndex<T, Integer, Integer> {

	public TwoDimIndexIntXInt(T[][][] arry) {
		super(arry);
	}

	@Override
	public int convertN(Integer value) {
		return value - rootNdxN;
	}

	@Override
	public int convertD(Integer value) {
		return value - rootNdxD;
	}

}
