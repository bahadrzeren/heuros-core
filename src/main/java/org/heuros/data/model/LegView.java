package org.heuros.data.model;

import java.time.LocalDateTime;

import org.heuros.core.data.base.View;

/**
 * Getter only class used in rule implementations to access Leg instances.
 * 
 * @author bahadrzeren
 *
 */
public interface LegView extends View {
	public String getCarrier();
	public int getFlightNo();
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

	public boolean isAnyHbDep();
	public boolean isAnyHbArr();
	public boolean isAnyNonHbDep();
	public boolean isAnyNonHbArr();
	public boolean isHbDep(int hbNdx);
	public boolean isHbArr(int hbNdx);
	public boolean isNonHbDep(int hbNdx);
	public boolean isNonHbArr(int hbNdx);

	public boolean isInFleet();
	public boolean isDeadheadable();
	public boolean isSpecialFlight();

	public boolean isCover();

	public int getBlockTimeInMins();

	public boolean hasAcChangeWith(LegView nl);

	public double getScore();

	/*
	 * TODO HB impl will be changed!
	 */
	public int getNumOfDutiesIncludes();
	public int getNumOfDutiesIncludesHbDep();
	public int getNumOfDutiesIncludesHbArr();
	public int getNumOfDutiesIncludesNonHbDep();
	public int getNumOfDutiesIncludesNonHbArr();
}
