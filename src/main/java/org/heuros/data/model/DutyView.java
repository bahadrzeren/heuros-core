package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.heuros.core.data.base.View;

public interface DutyView extends View {

	public DutyLegView getFirstDutyLeg();
	public DutyLegView getLastDutyLeg();
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

	public int getNumOfCriticalLegs();
	public int getNumOfAgDg();
	public int getNumOfSpecialFlights();

	public int getNumOfAnyHomebaseTouch();
	public int getNumOfDomTouch();
	public int getNumOfIntTouch();

	public int getNumOfAcChanges();

	public int getLongConnDiff();

	public int getBriefDurationInMinsHb();
	public int getBriefDurationInMinsNonHb();
	public int getBriefDurationInMins(Airport hb);
	public int getDebriefDurationInMins();

	public LocalDateTime getBriefTimeHb();
	public LocalDateTime getBriefTimeNonHb();
	public LocalDateTime getBriefTime(Airport hb);
	public LocalDateTime getDebriefTime();

	public LocalDateTime getBriefDayBeginningHb();
	public LocalDateTime getBriefDayBeginningNonHb();
	public LocalDateTime getBriefDayBeginning(Airport hb);
	public LocalDateTime getDebriefDayEnding();

	public LocalDate getBriefDayHb();
	public LocalDate getBriefDayNonHb();
	public LocalDate getBriefDay(Airport hb);
	public LocalDate getDebriefDay();

	public int getDutyDurationInMinsHb();
	public int getDutyDurationInMinsNonHb();
	public int getDutyDurationInMins(Airport hb);

	public int getNumOfDaysTouchedHb();
	public int getNumOfDaysTouchedNonHb();
	public int getNumOfDaysTouched(Airport hb);

	public boolean isEr();

	public int getRestDurationInMinsHbToHb();
	public int getRestDurationInMinsHbToNonHb();
	public int getRestDurationInMinsNonHbToHb();
	public int getRestDurationInMinsNonHbToNonHb();
	public int getRestDurationInMins(Airport hb);

	public LocalDateTime getNextBriefTimeHbToHb();
	public LocalDateTime getNextBriefTimeHbToNonHb();
	public LocalDateTime getNextBriefTimeNonHbToHb();
	public LocalDateTime getNextBriefTimeNonHbToNonHb();
	public LocalDateTime getNextBriefTimeHbTo(Airport hb);

	public int getAugmentedHb();
	public int getAugmentedNonHb();
	public int getAugmented(Airport hb);

	public boolean isInternational();
	public boolean isEarlyHb();
	public boolean isEarlyNonHb();
	public boolean isEarly(Airport hb);
	public boolean isHardHb();
	public boolean isHardNonHb();
	public boolean isHard(Airport hb);

	public int[] getLongestBlockTimesInMins();
	public int getLongestBlockTimeInMins();

	public boolean isValidHb();
	public boolean isValidNonHb();
	public boolean isValid(Airport hb);
}
