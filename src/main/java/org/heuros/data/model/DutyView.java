package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.heuros.core.data.base.View;

/**
 * Getter only class used in rule implementations to access Duty instances.
 * 
 * @author bahadrzeren
 *
 */
public interface DutyView extends View {

	public List<LegView> getLegs();

	public LegView getFirstLeg();
	public LegView getLastLeg();
	public LegView getSecondToLastLeg();
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

	public int getNumOfCriticalLegs();
	public int getNumOfAgDg();
	public int getNumOfSpecialFlights();

	public int getNumOfDomTouch();
	public int getNumOfIntTouch();

	public int getNumOfAnyHomebaseTouch();
	public int getNumOfHomebaseTouch(int hbNdx);

	public int getNumOfAcChanges();

	public int getLongConnDiff();

	public boolean isEr();
	public boolean isInternational();
	public int[] getLongestBlockTimesInMins();
	public int getLongestBlockTimeInMins();

	public int getBriefDurationInMins(int hbNdx);
	public LocalDateTime getBriefTime(int hbNdx);
	public LocalDateTime getBriefDayBeginning(int hbNdx);
	public LocalDate getBriefDay(int hbNdx);

	public int getDebriefDurationInMins(int hbNdx);
	public LocalDateTime getDebriefTime(int hbNdx);
	public LocalDateTime getDebriefDayEnding(int hbNdx);
	public LocalDate getDebriefDay(int hbNdx);

	public int getDutyDurationInMins(int hbNdx);
	public int getNumOfDaysTouched(int hbNdx);
	public int getRestDurationInMins(int hbNdx);
	public LocalDateTime getNextBriefTime(int hbNdx);
	public int getAugmented(int hbNdx);
	public boolean isEarly(int hbNdx);
	public boolean isHard(int hbNdx);
	public boolean isValid(int hbNdx);

	public int getTotalNumOfIncludingDutiesOfTheSameLegs();
}
