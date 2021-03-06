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
//	public boolean isCritical();
//	public boolean isCriticalWoDh();

	public boolean isCover();

	public int getBlockTimeInMins();

	public boolean hasAcChangeWith(LegView nl);

	public boolean hasPair(int hbNdx);
	public boolean hasHbDepArrDutyPair(int hbNdx);
	public boolean hasHbDepDutyPair(int hbNdx);
	public boolean hasNonHbDutyPair(int hbNdx);
	public boolean hasHbArrDutyPair(int hbNdx);

//	public int getNumOfIncludingDuties();
//	public int getNumOfIncludingDutiesWoDh();
//
//	public int getNumOfIncludingEffectiveDuties();
//	public int getNumOfIncludingEffectiveDutiesWoDh();

	/*
	 * TODO HB impl will be changed!
	 */
//	public int getNumOfIncludingHbDepDuties(int hbNdx);
//	public int getNumOfIncludingHbArrDuties(int hbNdx);
//	public int getNumOfIncludingNonHbDepDuties(int hbNdx);
//	public int getNumOfIncludingNonHbArrDuties(int hbNdx);
}
