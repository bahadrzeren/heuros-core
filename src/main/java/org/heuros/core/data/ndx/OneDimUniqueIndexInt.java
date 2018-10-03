package org.heuros.core.data.ndx;

import org.heuros.core.data.base.View;

/**
 * Implementation of the one dimensional Integer index.
 * 
 * @author bahadrzeren
 *
 * @param <T> Type of the instances to be indexed.
 */
public class OneDimUniqueIndexInt<T extends View> extends OneDimUniqueIndex<T, Integer> {

	public OneDimUniqueIndexInt(T[][] arry) {
		super(arry);
	}

	@Override
	public int convert(Integer value) {
		return value - rootNdx;
	}
}
