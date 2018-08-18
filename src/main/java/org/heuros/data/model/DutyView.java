package org.heuros.data.model;

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
	public int getNumOfSpecFlights();

	public int getNumOfAnyHomebaseTouch();
	public int getNumOfDomTouch();
	public int getNumOfIntTouch();

	public int getNumOfAcChanges();

	public int[] getLongestBlockTimesInMins();
	public int getLongestBlockTimeInMins();



	public boolean isValidHb();
	public boolean isValidNonHb();

	public int getAugmentedHb();
	public int getAugmentedNonHb();
	public boolean isErHb();
	public boolean isErNonHb();

	public int getRestDurationInMinsHb();
	public int getRestDurationInMinsNonHb();

	public int getDutyDurationInMinsHb();
	public int getDutyDurationInMinsNonHb();

	public int getNumOfDaysTouched();

	public LocalDateTime getBriefTimeHb();
	public LocalDateTime getBriefTimeNonHb();
	public LocalDateTime getDebriefTime();

	public LocalDateTime getBriefDayBeginningHb();
	public LocalDateTime getBriefDayBeginningNonHb();
	public LocalDateTime getDebriefDayEnding();
	public int getBriefDayHb();
	public int getBriefDayNonHb();
	public int getDebriefDay();

	public int getBriefDurationInMinsHb();
	public int getBriefDurationInMinsNonHb();
	public int getDebriefDurationInMins();

	public LocalDateTime getNextBriefTimeHb();
	public LocalDateTime getNextBriefTimeNonHb();

	public boolean isHard();
	public boolean isEarly();

	public int getLongConnDiff();
}
