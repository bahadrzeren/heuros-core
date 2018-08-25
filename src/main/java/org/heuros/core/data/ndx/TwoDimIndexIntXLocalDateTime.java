package org.heuros.core.data.ndx;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.heuros.core.data.base.View;

public class TwoDimIndexIntXLocalDateTime<T extends View> extends TwoDimIndex<T, Integer, LocalDateTime> {

	protected static LocalDateTime referenceDateTime = LocalDateTime.of(2010, Month.JANUARY, 1, 0, 0, 0, 0);

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
