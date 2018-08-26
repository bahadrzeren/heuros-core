package org.heuros.core.data.ndx;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.heuros.core.data.base.View;

public class TwoDimIndexIntXLocalDateTime<T extends View> extends TwoDimIndex<T, Integer, LocalDateTime> {

	protected static LocalDateTime referenceDateTime = LocalDateTime.of(2012, Month.JULY, 7, 12, 30, 0, 0);

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
