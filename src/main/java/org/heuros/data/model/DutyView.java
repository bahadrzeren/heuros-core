package org.heuros.data.model;

import java.time.LocalDateTime;

import org.heuros.core.data.base.View;

public interface DutyView extends View {

	public boolean isValidHb();
	public boolean isValidNonHb();

	public int getAugmentedHb();
	public int getAugmentedNonHb();
	public boolean isErHb();
	public boolean isErNonHb();

	public int getRestDurationHb();
	public int getRestDurationNonHb();

	public int getDutyDurationHb();
	public int getDutyDurationNonHb();

	public int getBlockTime();
	public int getBlockTimeActive();
	public int getBlockTimePassive();

	public int getNumOfLegs();
	public int getNumOfLegsActive();
	public int getNumOfLegsPassive();
	public int getNumOfLegsIntToDom();
	public int getNumOfLegsDomToInt();

	public int getNumOfDaysTouched();

	public int[] getLongestBlockTimes();
	public int getLongestBlockTime();

	public int getNumOfAnyHomebaseTouch();
	public int getNumOfDomTouch();
	public int getNumOfIntTouch();

	public LocalDateTime getBriefTimeHb();
	public LocalDateTime getBriefTimeNonHb();
	public LocalDateTime getDebriefTime();

	public LocalDateTime getBriefDayBeginningHb();
	public LocalDateTime getBriefDayBeginningNonHb();
	public LocalDateTime getDebriefDayEnding();
	public int getBriefDayHb();
	public int getBriefDayNonHb();
	public int getDebriefDay();

	public int getBriefDurationHb();
	public int getBriefDurationNonHb();
	public int getDebriefDuration();

	public LocalDateTime getNextBriefTimeHb();
	public LocalDateTime getNextBriefTimeNonHb();

	public boolean isHard();
	public boolean isEarly();

	public int getNumOfCriticalLegs();
	public int getNumOfAgDg();
	public int getNumOfSpecFlights();

	public int getLongConnDiff();
	public int getNumOfAcChanges();
	public int getNumOfSpecialDHs();
}
