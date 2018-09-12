package org.heuros.data.model;

import org.heuros.core.data.base.View;

/**
 * Getter only class used in rule implementations to access Airport instances.
 * 
 * @author bahadrzeren
 *
 */
public interface AirportView extends View {
	public String getCode();

	public boolean isAnyHb();
	public boolean isAnyNonHb();
	public int getHbNdx();
	public boolean isHb(int hbNdx);
	public boolean isNonHb(int hbNdx);
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
