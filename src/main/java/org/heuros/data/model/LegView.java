package org.heuros.data.model;

import java.time.LocalDateTime;

import org.heuros.core.data.base.View;

public interface LegView extends View {
	public String getCarrier();
	public int getFligtNo();
	public String getSuffix();
	public String getDep();
	public String getArr();
	public AirportView getDepAirport();
	public AirportView getArrAirport();
	public String getAcType();
	public String getServiceType();
	public LocalDateTime getSobt();
	public LocalDateTime getSibt();
	public int getDepOffset();
	public int getArrOffset();
	public int getAcSequence();
	public boolean isNeedsCockpitCrew();
	public boolean isNeedsCabinCrew();
}
