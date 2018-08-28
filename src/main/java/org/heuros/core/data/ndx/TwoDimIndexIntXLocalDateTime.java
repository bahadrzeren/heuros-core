package org.heuros.core.data.ndx;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.heuros.core.data.base.View;

/**
 * Implementation of the two dimensional Integer and datetime index.
 * 
 * @author bahadrzeren
 *
 * @param <T> Type of the instances to be indexed.
 */
public class TwoDimIndexIntXLocalDateTime<T extends View> extends TwoDimIndex<T, Integer, LocalDateTime> {

	/**
	 * ReferenceDateTime is used to obtain integer (hourly) values for each datetime inputs 
	 * by calculating duration between referenceDateTime and input datetime.
	 */
	protected static LocalDateTime referenceDateTime = LocalDateTime.of(2013, Month.DECEMBER, 1, 0, 0, 0, 0);

	public TwoDimIndexIntXLocalDateTime(T[][][] arry) {
		super(arry);
	}

	@Override
	public int convertN(Integer value) {
		return value - rootNdxN;
	}

	@Override
	public int convertD(LocalDateTime value) {
		return ((int) ChronoUnit.HOURS.between(referenceDateTime, value)) - rootNdxD;
	}

}
