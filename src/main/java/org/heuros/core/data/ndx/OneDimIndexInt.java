package org.heuros.core.data.ndx;

import org.heuros.core.data.base.View;

public class OneDimIndexInt<T extends View> extends OneDimIndex<T, Integer> {

	public OneDimIndexInt(T[][] arry) {
		super(arry);
	}

	@Override
	public int convert(Integer value) {
		return value - rootNdx;
	}
}
