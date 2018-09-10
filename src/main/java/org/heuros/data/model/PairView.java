package org.heuros.data.model;

import org.heuros.core.data.base.View;

/**
 * Getter only class used in rule implementations to access Pair instances.
 * 
 * @author bahadrzeren
 *
 */
public interface PairView extends View {

	public int getHbNdx();

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

	public int getBriefDurationInMins();
	public int getDutyDurationInMins();
	public int getDebriefDurationInMins();
	public int getRestDurationInMins();

	public int getNumOfDaysTouched();

	public int getNumOfDuties();
	public int getNumOfInternationalDuties();
	public int getNumOfEarlyDuties();
	public int getNumOfHardDuties();
	public int getNumOfAugmentedDuties();
	public int getNumOfErDuties();
}
