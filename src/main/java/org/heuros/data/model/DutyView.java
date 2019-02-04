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

	public List<Leg> getLegs();

	public Leg getFirstLeg();
	public Leg getLastLeg();
	public Leg getSecondToLastLeg();
	public Leg getSecondLeg();
	public AirportView getFirstDepAirport();
	public AirportView getLastArrAirport();

	public boolean isAnyHbDep();
	public boolean isAnyHbArr();
	public boolean isAnyNonHbDep();
	public boolean isAnyNonHbArr();
	public boolean isHbDep(int hbNdx);
	public boolean isHbArr(int hbNdx);
	public boolean isNonHbDep(int hbNdx);
	public boolean isNonHbArr(int hbNdx);

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

	public void setLongestBlockTimeInMins(int blockTime);
	public int getLongestBlockTimeInMins();
	public int removeLongestBlockTimeInMins();
	public void removeAllLongestBlockTimeInMins();

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

	public boolean isValidated();
	public boolean isValid(int hbNdx);

	public int getMinNumOfAlternativeDuties();
	public int getMinNumOfAlternativeDutiesWoDh();
	public int getMaxNumOfAlternativeDuties();
	public int getMaxNumOfAlternativeDutiesWoDh();
	public int getTotalNumOfAlternativeDuties();
	public int getTotalNumOfAlternativeDutiesWoDh();

	public boolean hasPairing(int hbNdx);

	public void setMinNextBriefTime(int hbNdx, LocalDateTime minPrevDebriefTime);
	public LocalDateTime getMinNextBriefTime(int hbNdx);
	public void setMinPrevDebriefTime(int hbNdx, LocalDateTime minPrevDebriefTime);
	public LocalDateTime getMinPrevDebriefTime(int hbNdx);
}
