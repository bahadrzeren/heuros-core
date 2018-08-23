package org.heuros.core.data.ndx;

public class OneDimIndexInt<T> extends OneDimIndex<T, Integer> {

	public OneDimIndexInt(T[][] arry) {
		super(arry);
	}

	@Override
	public int convert(Integer value) {
		return value;
	}
}
