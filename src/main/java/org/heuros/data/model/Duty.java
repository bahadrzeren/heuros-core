package org.heuros.data.model;

import java.time.LocalDateTime;
import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Duty extends AbstractModel implements DutyView {

	private List<DutyLeg> dutyLegs;

	private boolean validHb = true;
	private boolean validNonHb = true;

	private int augmentedHb = 0;
	private int augmentedNonHb = 0;
	private boolean erHb = false;
	private boolean erNonHb = false;

	private int restDurationHb = 0;
	private int restDurationNonHb = 0;

	private int dutyDurationHb = 0;
	private int dutyDurationNonHb = 0;

	private int blockTime = 0;
	private int blockTimeActive = 0;
	private int blockTimePassive = 0;

	private int numOfLegs = 0;
	private int numOfLegsActive = 0;
	private int numOfLegsPassive = 0;
	private int numOfLegsIntToDom = 0;
	private int numOfLegsDomToInt = 0;

	private int numOfDaysTouched = 0;

	private int[] longestBlockTimes = new int[10];
	private int longestBlockTime = 0;

	private int numOfAnyHomebaseTouch = 0;
	private int numOfDomTouch = 0;
	private int numOfIntTouch = 0;

	private LocalDateTime briefTimeHb = null;
	private LocalDateTime briefTimeNonHb = null;
	private LocalDateTime debriefTime = null;

	private LocalDateTime briefDayBeginningHb = null;
	private LocalDateTime briefDayBeginningNonHb = null;
	private LocalDateTime debriefDayEnding = null;
	private int briefDayHb = 0;
	private int briefDayNonHb = 0;
	private int debriefDay = 0;

	private int briefDurationHb = 0;
	private int briefDurationNonHb = 0;
	private int debriefDuration = 0;

	private LocalDateTime nextBriefTimeHb = null;
	private LocalDateTime nextBriefTimeNonHb = null;

	private boolean hard = false;
	private boolean early = false;

	private int numOfCriticalLegs = 0;
	private int numOfAgDg = 0;
	private int numOfSpecFlights = 0;

	private int longConnDiff = 0;
	private int numOfAcChanges = 0;
	private int numOfSpecialDHs = 0;

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}

	public boolean isValidHb() {
		return validHb;
	}

	public void setValidHb(boolean validHb) {
		this.validHb = validHb;
	}

	public boolean isValidNonHb() {
		return validNonHb;
	}

	public void setValidNonHb(boolean validNonHb) {
		this.validNonHb = validNonHb;
	}

	public int getAugmentedHb() {
		return augmentedHb;
	}

	public void setAugmentedHb(int augmentedHb) {
		this.augmentedHb = augmentedHb;
	}

	public int getAugmentedNonHb() {
		return augmentedNonHb;
	}

	public void setAugmentedNonHb(int augmentedNonHb) {
		this.augmentedNonHb = augmentedNonHb;
	}

	public boolean isErHb() {
		return erHb;
	}

	public void setErHb(boolean erHb) {
		this.erHb = erHb;
	}

	public boolean isErNonHb() {
		return erNonHb;
	}

	public void setErNonHb(boolean erNonHb) {
		this.erNonHb = erNonHb;
	}

	public int getRestDurationHb() {
		return restDurationHb;
	}

	public void setRestDurationHb(int restDurationHb) {
		this.restDurationHb = restDurationHb;
	}

	public int getRestDurationNonHb() {
		return restDurationNonHb;
	}

	public void setRestDurationNonHb(int restDurationNonHb) {
		this.restDurationNonHb = restDurationNonHb;
	}

	public int getDutyDurationHb() {
		return dutyDurationHb;
	}

	public void setDutyDurationHb(int dutyDurationHb) {
		this.dutyDurationHb = dutyDurationHb;
	}

	public int getDutyDurationNonHb() {
		return dutyDurationNonHb;
	}

	public void setDutyDurationNonHb(int dutyDurationNonHb) {
		this.dutyDurationNonHb = dutyDurationNonHb;
	}

	public int getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(int blockTime) {
		this.blockTime = blockTime;
	}

	public int getBlockTimeActive() {
		return blockTimeActive;
	}

	public void setBlockTimeActive(int blockTimeActive) {
		this.blockTimeActive = blockTimeActive;
	}

	public int getBlockTimePassive() {
		return blockTimePassive;
	}

	public void setBlockTimePassive(int blockTimePassive) {
		this.blockTimePassive = blockTimePassive;
	}

	public int getNumOfLegs() {
		return numOfLegs;
	}

	public void setNumOfLegs(int numOfLegs) {
		this.numOfLegs = numOfLegs;
	}

	public int getNumOfLegsActive() {
		return numOfLegsActive;
	}

	public void setNumOfLegsActive(int numOfLegsActive) {
		this.numOfLegsActive = numOfLegsActive;
	}

	public int getNumOfLegsPassive() {
		return numOfLegsPassive;
	}

	public void setNumOfLegsPassive(int numOfLegsPassive) {
		this.numOfLegsPassive = numOfLegsPassive;
	}

	public int getNumOfLegsIntToDom() {
		return numOfLegsIntToDom;
	}

	public void setNumOfLegsIntToDom(int numOfLegsIntToDom) {
		this.numOfLegsIntToDom = numOfLegsIntToDom;
	}

	public int getNumOfLegsDomToInt() {
		return numOfLegsDomToInt;
	}

	public void setNumOfLegsDomToInt(int numOfLegsDomToInt) {
		this.numOfLegsDomToInt = numOfLegsDomToInt;
	}

	public int getNumOfDaysTouched() {
		return numOfDaysTouched;
	}

	public void setNumOfDaysTouched(int numOfDaysTouched) {
		this.numOfDaysTouched = numOfDaysTouched;
	}

	public int[] getLongestBlockTimes() {
		return longestBlockTimes;
	}

	public void setLongestBlockTimes(int[] longestBlockTimes) {
		this.longestBlockTimes = longestBlockTimes;
	}

	public int getLongestBlockTime() {
		return longestBlockTime;
	}

	public void setLongestBlockTime(int longestBlockTime) {
		this.longestBlockTime = longestBlockTime;
	}

	public int getNumOfAnyHomebaseTouch() {
		return numOfAnyHomebaseTouch;
	}

	public void setNumOfAnyHomebaseTouch(int numOfAnyHomebaseTouch) {
		this.numOfAnyHomebaseTouch = numOfAnyHomebaseTouch;
	}

	public int getNumOfDomTouch() {
		return numOfDomTouch;
	}

	public void setNumOfDomTouch(int numOfDomTouch) {
		this.numOfDomTouch = numOfDomTouch;
	}

	public int getNumOfIntTouch() {
		return numOfIntTouch;
	}

	public void setNumOfIntTouch(int numOfIntTouch) {
		this.numOfIntTouch = numOfIntTouch;
	}

	public LocalDateTime getBriefTimeHb() {
		return briefTimeHb;
	}

	public void setBriefTimeHb(LocalDateTime briefTimeHb) {
		this.briefTimeHb = briefTimeHb;
	}

	public LocalDateTime getBriefTimeNonHb() {
		return briefTimeNonHb;
	}

	public void setBriefTimeNonHb(LocalDateTime briefTimeNonHb) {
		this.briefTimeNonHb = briefTimeNonHb;
	}

	public LocalDateTime getDebriefTime() {
		return debriefTime;
	}

	public void setDebriefTime(LocalDateTime debriefTime) {
		this.debriefTime = debriefTime;
	}

	public LocalDateTime getBriefDayBeginningHb() {
		return briefDayBeginningHb;
	}

	public void setBriefDayBeginningHb(LocalDateTime briefDayBeginningHb) {
		this.briefDayBeginningHb = briefDayBeginningHb;
	}

	public LocalDateTime getBriefDayBeginningNonHb() {
		return briefDayBeginningNonHb;
	}

	public void setBriefDayBeginningNonHb(LocalDateTime briefDayBeginningNonHb) {
		this.briefDayBeginningNonHb = briefDayBeginningNonHb;
	}

	public LocalDateTime getDebriefDayEnding() {
		return debriefDayEnding;
	}

	public void setDebriefDayEnding(LocalDateTime debriefDayEnding) {
		this.debriefDayEnding = debriefDayEnding;
	}

	public int getBriefDayHb() {
		return briefDayHb;
	}

	public void setBriefDayHb(int briefDayHb) {
		this.briefDayHb = briefDayHb;
	}

	public int getBriefDayNonHb() {
		return briefDayNonHb;
	}

	public void setBriefDayNonHb(int briefDayNonHb) {
		this.briefDayNonHb = briefDayNonHb;
	}

	public int getDebriefDay() {
		return debriefDay;
	}

	public void setDebriefDay(int debriefDay) {
		this.debriefDay = debriefDay;
	}

	public int getBriefDurationHb() {
		return briefDurationHb;
	}

	public void setBriefDurationHb(int briefDurationHb) {
		this.briefDurationHb = briefDurationHb;
	}

	public int getBriefDurationNonHb() {
		return briefDurationNonHb;
	}

	public void setBriefDurationNonHb(int briefDurationNonHb) {
		this.briefDurationNonHb = briefDurationNonHb;
	}

	public int getDebriefDuration() {
		return debriefDuration;
	}

	public void setDebriefDuration(int debriefDuration) {
		this.debriefDuration = debriefDuration;
	}

	public LocalDateTime getNextBriefTimeHb() {
		return nextBriefTimeHb;
	}

	public void setNextBriefTimeHb(LocalDateTime nextBriefTimeHb) {
		this.nextBriefTimeHb = nextBriefTimeHb;
	}

	public LocalDateTime getNextBriefTimeNonHb() {
		return nextBriefTimeNonHb;
	}

	public void setNextBriefTimeNonHb(LocalDateTime nextBriefTimeNonHb) {
		this.nextBriefTimeNonHb = nextBriefTimeNonHb;
	}

	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	public boolean isEarly() {
		return early;
	}

	public void setEarly(boolean early) {
		this.early = early;
	}

	public int getNumOfCriticalLegs() {
		return numOfCriticalLegs;
	}

	public void setNumOfCriticalLegs(int numOfCriticalLegs) {
		this.numOfCriticalLegs = numOfCriticalLegs;
	}

	public int getNumOfAgDg() {
		return numOfAgDg;
	}

	public void setNumOfAgDg(int numOfAgDg) {
		this.numOfAgDg = numOfAgDg;
	}

	public int getNumOfSpecFlights() {
		return numOfSpecFlights;
	}

	public void setNumOfSpecFlights(int numOfSpecFlights) {
		this.numOfSpecFlights = numOfSpecFlights;
	}

	public int getLongConnDiff() {
		return longConnDiff;
	}

	public void setLongConnDiff(int longConnDiff) {
		this.longConnDiff = longConnDiff;
	}

	public int getNumOfAcChanges() {
		return numOfAcChanges;
	}

	public void setNumOfAcChanges(int numOfAcChanges) {
		this.numOfAcChanges = numOfAcChanges;
	}

	public int getNumOfSpecialDHs() {
		return numOfSpecialDHs;
	}

	public void setNumOfSpecialDHs(int numOfSpecialDHs) {
		this.numOfSpecialDHs = numOfSpecialDHs;
	}

}
