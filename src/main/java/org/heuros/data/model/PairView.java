package org.heuros.data.model;

import org.heuros.core.data.base.View;

/**
 * Getter only class used in rule implementations to access Pair instances.
 * 
 * @author bahadrzeren
 *
 */
public interface PairView extends View {

	public boolean isComplete();
	public boolean isComplete(int hbNdx);
	/**
	 * Returns the ndx if homebase if pairing is complete.
	 * 
	 * @return homebase ndx of the pairing.
	 */
	public int getHbNdx();
//	/**
//	 * Returns the homebase ndx of departure airport if it is really a homebase.
//	 * 
//	 * @return homebase ndx of the departure airport.
//	 */
//	public int getDepHbNdx();
//	/**
//	 * Returns the homebase ndx of arrival airport if it is really a homebase.
//	 * 
//	 * @return homebase ndx of the arrival airport.
//	 */
//	public int getArrHbNdx();
//	public boolean isHbDep(int hbNdx);
//	public boolean isHbArr(int hbNdx);

	public DutyView getFirstDuty();
	public DutyView getLastDuty();
	public LegView getFirstLeg();
	public LegView getLastLeg();
	public AirportView getFirstDepAirport();
	public AirportView getLastArrAirport();

	public int getBlockTimeInMins();
	public int getBlockTimeInMinsActive();
	public int getBlockTimeInMinsPassive();

	public int getNumOfLegs();
	public int getNumOfLegsActive();
	public int getNumOfLegsPassive();
	public int getNumOfLegsIntToDom();
	public int getNumOfLegsDomToInt();

	public int getNumOfDuties();

//	public int getBriefDurationInMins(int hbNdx);
//	public int getDutyDurationInMins(int hbNdx);
//	public int getDebriefDurationInMins(int hbNdx);
//	public int getRestDurationInMins(int hbNdx);
//
//	public int getNumOfDaysTouched(int hbNdx);
//
//	public int getNumOfEarlyDuties(int hbNdx);
//	public int getNumOfHardDuties(int hbNdx);
//	public int getNumOfAugmentedDuties(int hbNdx);

	public int getBriefDurationInMins();
	public int getDutyDurationInMins();
	public int getDebriefDurationInMins();
	public int getRestDurationInMins();

	public int getNumOfDaysTouched();

	public int getNumOfEarlyDuties();
	public int getNumOfHardDuties();
	public int getNumOfAugmentedDuties();

	public int getNumOfInternationalDuties();
	public int getNumOfErDuties();
}
