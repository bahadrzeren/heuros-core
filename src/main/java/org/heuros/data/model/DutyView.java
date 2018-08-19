package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.heuros.core.data.base.View;

public interface DutyView extends View {

	public void append(DutyLegView dutyLegView);
	public DutyLegView removeLast();
	public DutyLegView getFirstDutyLeg();
	public DutyLegView getLastDutyLeg();
	public LegView getFirstLeg();
	public LegView getLastLeg();

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
	public int getDebriefDurationInMins();

	public LocalDateTime getBriefTimeHb();
	public LocalDateTime getBriefTimeNonHb();
	public LocalDateTime getDebriefTime();

	public LocalDateTime getBriefDayBeginningHb();
	public LocalDateTime getBriefDayBeginningNonHb();
	public LocalDateTime getDebriefDayEnding();

	public LocalDate getBriefDayHb();
	public LocalDate getBriefDayNonHb();
	public LocalDate getDebriefDay();

	public int getDutyDurationInMinsHb();
	public int getDutyDurationInMinsNonHb();

	public int getNumOfDaysTouchedHb();
	public int getNumOfDaysTouchedNonHb();

	public boolean isEr();

	public int getRestDurationInMinsHbToHb();
	public int getRestDurationInMinsHbToNonHb();
	public int getRestDurationInMinsNonHbToHb();
	public int getRestDurationInMinsNonHbToNonHb();

	public LocalDateTime getNextBriefTimeHbToHb();
	public LocalDateTime getNextBriefTimeHbToNonHb();
	public LocalDateTime getNextBriefTimeNonHbToHb();
	public LocalDateTime getNextBriefTimeNonHbToNonHb();

	public int getAugmentedHb();
	public int getAugmentedNonHb();

	public boolean isEarlyHb();
	public boolean isEarlyNonHb();
	public boolean isHardHb();
	public boolean isHardNonHb();

	public int[] getLongestBlockTimesInMins();
	public int getLongestBlockTimeInMins();



	public boolean isValidHb();
	public boolean isValidNonHb();
}
