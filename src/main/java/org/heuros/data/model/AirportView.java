package org.heuros.data.model;

import org.heuros.core.data.base.View;

public interface AirportView extends View {
	public String getCode();

	public boolean isHb();
	public boolean isNonHb();
	public boolean isDomestic();
	public boolean isInternational();

	public boolean isDhNotAllowedIfHBDepOrArr();
	public boolean isCritical();
	public boolean isAgDg();
	public boolean isOneDutyStation();
	public boolean isAcChangeAllowed();
	public boolean isSpecialEuroStation();
	public boolean isLayoverAllowed();
	public boolean isEndDutyIfTouches();
	public boolean isMandatoryFirstLayover();
	public int getGroupId();
	public boolean isLegConnectionExceptionStation();
}
