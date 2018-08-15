package org.heuros.data.model;

import org.heuros.core.data.base.View;

public interface AirportView extends View {
	public String getCode();

	public boolean isHb();
	public boolean isNonHB();
	public boolean isDomestic();
	public boolean isInternational();

	public boolean isDhAlowedIfHBDepOrArr();
	public boolean isCritical();
	public boolean isAgDg();
	public boolean isOneDutyStat();
	public boolean isAcChangeAllowed();
	public boolean isSpecEuroStat();
	public boolean isLayoverAllowed();
	public boolean isEndDutyIfTouches();
	public boolean isMandatoryFirstLayover();
	public int getGroupId();
	public boolean isLegConnExcStation();
	public boolean isHaccStat();
}
