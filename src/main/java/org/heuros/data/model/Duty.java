package org.heuros.data.model;

import java.time.LocalDateTime;
import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Duty extends AbstractModel implements DutyView {

	private List<DutyLegView> dutyLegs;

	private int blockTimeInMins = 0;
	private int blockTimeInMinsActive = 0;
	private int blockTimeInMinsPassive = 0;

	private int numOfLegs = 0;
	private int numOfLegsActive = 0;
	private int numOfLegsPassive = 0;
	private int numOfLegsIntToDom = 0;
	private int numOfLegsDomToInt = 0;

	private int numOfCriticalLegs = 0;
	private int numOfAgDg = 0;
	private int numOfSpecFlights = 0;

	private int numOfAnyHomebaseTouch = 0;
	private int numOfDomTouch = 0;
	private int numOfIntTouch = 0;

	private int numOfAcChanges = 0;

	private int longConnDiff = 0;

	private int[] longestBlockTimesInMins = new int[15];
	private int longestBlockTimeInMins = 0;



	private boolean validHb = true;
	private boolean validNonHb = true;

	private int augmentedHb = 0;
	private int augmentedNonHb = 0;
	private boolean erHb = false;
	private boolean erNonHb = false;

	private int restDurationInMinsHb = 0;
	private int restDurationInMinsNonHb = 0;

	private int dutyDurationInMinsHb = 0;
	private int dutyDurationInMinsNonHb = 0;

	private int numOfDaysTouched = 0;

	private LocalDateTime briefTimeHb = null;
	private LocalDateTime briefTimeNonHb = null;
	private LocalDateTime debriefTime = null;

	private LocalDateTime briefDayBeginningHb = null;
	private LocalDateTime briefDayBeginningNonHb = null;
	private LocalDateTime debriefDayEnding = null;
	private int briefDayHb = 0;
	private int briefDayNonHb = 0;
	private int debriefDay = 0;

	private int briefDurationInMinsHb = 0;
	private int briefDurationInMinsNonHb = 0;
	private int debriefDurationInMins = 0;

	private LocalDateTime nextBriefTimeHb = null;
	private LocalDateTime nextBriefTimeNonHb = null;

	private boolean hard = false;
	private boolean early = false;

	public void append(DutyLegView dutyLegView) {
		this.dutyLegs.add(dutyLegView);
	}
	public DutyLegView removeLast() {
		if (this.dutyLegs.size() > 0)
			return this.dutyLegs.remove(this.dutyLegs.size() - 1);
		return null;
	}
	public DutyLegView getFirstDutyLeg() {
		if (this.dutyLegs.size() > 0)
			return this.dutyLegs.get(0);
		return null;
	}
	public DutyLegView getLastDutyLeg() {
		if (this.dutyLegs.size() > 0)
			return this.dutyLegs.get(this.dutyLegs.size() - 1);
		return null;		
	}
	public LegView getFirstLeg() {
		if (this.dutyLegs.size() > 0)
			return this.dutyLegs.get(0).getLeg();
		return null;		
	}
	public LegView getLastLeg() {
		if (this.dutyLegs.size() > 0)
			return this.dutyLegs.get(this.dutyLegs.size() - 1).getLeg();
		return null;		
	}

	public List<DutyLegView> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLegView> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}

	public int getBlockTimeInMins() {
		return blockTimeInMins;
	}

	public void setBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins = blockTimeInMins;
	}

	public void incBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins += blockTimeInMins;
	}

	public int getBlockTimeInMinsActive() {
		return blockTimeInMinsActive;
	}

	public void setBlockTimeInMinsActive(int blockTimeInMinsActive) {
		this.blockTimeInMinsActive = blockTimeInMinsActive;
	}

	public void incBlockTimeInMinsActive(int blockTimeInMinsActive) {
		this.blockTimeInMinsActive += blockTimeInMinsActive;
	}

	public int getBlockTimeInMinsPassive() {
		return blockTimeInMinsPassive;
	}

	public void setBlockTimeInMinsPassive(int blockTimeInMinsPassive) {
		this.blockTimeInMinsPassive = blockTimeInMinsPassive;
	}

	public void incBlockTimeInMinsPassive(int blockTimeInMinsPassive) {
		this.blockTimeInMinsPassive += blockTimeInMinsPassive;
	}

	public int getNumOfLegs() {
		return numOfLegs;
	}

	public void setNumOfLegs(int numOfLegs) {
		this.numOfLegs = numOfLegs;
	}

	public void incNumOfLegs(int numOfLegs) {
		this.numOfLegs += numOfLegs;
	}

	public int getNumOfLegsActive() {
		return numOfLegsActive;
	}

	public void setNumOfLegsActive(int numOfLegsActive) {
		this.numOfLegsActive = numOfLegsActive;
	}

	public void incNumOfLegsActive(int numOfLegsActive) {
		this.numOfLegsActive += numOfLegsActive;
	}

	public int getNumOfLegsPassive() {
		return numOfLegsPassive;
	}

	public void setNumOfLegsPassive(int numOfLegsPassive) {
		this.numOfLegsPassive = numOfLegsPassive;
	}

	public void incNumOfLegsPassive(int numOfLegsPassive) {
		this.numOfLegsPassive += numOfLegsPassive;
	}

	public int getNumOfLegsIntToDom() {
		return numOfLegsIntToDom;
	}

	public void setNumOfLegsIntToDom(int numOfLegsIntToDom) {
		this.numOfLegsIntToDom = numOfLegsIntToDom;
	}

	public void incNumOfLegsIntToDom(int numOfLegsIntToDom) {
		this.numOfLegsIntToDom += numOfLegsIntToDom;
	}

	public int getNumOfLegsDomToInt() {
		return numOfLegsDomToInt;
	}

	public void setNumOfLegsDomToInt(int numOfLegsDomToInt) {
		this.numOfLegsDomToInt = numOfLegsDomToInt;
	}

	public void incNumOfLegsDomToInt(int numOfLegsDomToInt) {
		this.numOfLegsDomToInt += numOfLegsDomToInt;
	}

	public int getNumOfCriticalLegs() {
		return numOfCriticalLegs;
	}

	public void setNumOfCriticalLegs(int numOfCriticalLegs) {
		this.numOfCriticalLegs = numOfCriticalLegs;
	}

	public void incNumOfCriticalLegs(int numOfCriticalLegs) {
		this.numOfCriticalLegs += numOfCriticalLegs;
	}

	public int getNumOfAgDg() {
		return numOfAgDg;
	}

	public void setNumOfAgDg(int numOfAgDg) {
		this.numOfAgDg = numOfAgDg;
	}

	public void incNumOfAgDg(int numOfAgDg) {
		this.numOfAgDg += numOfAgDg;
	}

	public int getNumOfSpecFlights() {
		return numOfSpecFlights;
	}

	public void setNumOfSpecFlights(int numOfSpecFlights) {
		this.numOfSpecFlights = numOfSpecFlights;
	}

	public void incNumOfSpecFlights(int numOfSpecFlights) {
		this.numOfSpecFlights += numOfSpecFlights;
	}

	public int getNumOfAnyHomebaseTouch() {
		return numOfAnyHomebaseTouch;
	}

	public void setNumOfAnyHomebaseTouch(int numOfAnyHomebaseTouch) {
		this.numOfAnyHomebaseTouch = numOfAnyHomebaseTouch;
	}

	public void incNumOfAnyHomebaseTouch(int numOfAnyHomebaseTouch) {
		this.numOfAnyHomebaseTouch += numOfAnyHomebaseTouch;
	}

	public int getNumOfDomTouch() {
		return numOfDomTouch;
	}

	public void setNumOfDomTouch(int numOfDomTouch) {
		this.numOfDomTouch = numOfDomTouch;
	}

	public void incNumOfDomTouch(int numOfDomTouch) {
		this.numOfDomTouch += numOfDomTouch;
	}

	public int getNumOfIntTouch() {
		return numOfIntTouch;
	}

	public void setNumOfIntTouch(int numOfIntTouch) {
		this.numOfIntTouch = numOfIntTouch;
	}

	public void incNumOfIntTouch(int numOfIntTouch) {
		this.numOfIntTouch += numOfIntTouch;
	}

	public int getNumOfAcChanges() {
		return numOfAcChanges;
	}

	public void setNumOfAcChanges(int numOfAcChanges) {
		this.numOfAcChanges = numOfAcChanges;
	}

	public void incNumOfAcChanges(int numOfAcChanges) {
		this.numOfAcChanges += numOfAcChanges;
	}

	public int getLongConnDiff() {
		return longConnDiff;
	}

	public void setLongConnDiff(int longConnDiff) {
		this.longConnDiff = longConnDiff;
	}

	public void incLongConnDiff(int longConnDiff) {
		this.longConnDiff += longConnDiff;
	}

	public int[] getLongestBlockTimesInMins() {
		return longestBlockTimesInMins;
	}

	public void setLongestBlockTimesInMins(int[] longestBlockTimesInMins) {
		this.longestBlockTimesInMins = longestBlockTimesInMins;
	}

	public int getLongestBlockTimeInMins() {
		return longestBlockTimeInMins;
	}

	public void setLongestBlockTimeInMins(int longestBlockTimeInMins) {
		this.longestBlockTimeInMins = longestBlockTimeInMins;
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

	public int getRestDurationInMinsHb() {
		return restDurationInMinsHb;
	}

	public void setRestDurationInMinsHb(int restDurationInMinsHb) {
		this.restDurationInMinsHb = restDurationInMinsHb;
	}

	public int getRestDurationInMinsNonHb() {
		return restDurationInMinsNonHb;
	}

	public void setRestDurationInMinsNonHb(int restDurationInMinsNonHb) {
		this.restDurationInMinsNonHb = restDurationInMinsNonHb;
	}

	public int getDutyDurationInMinsHb() {
		return dutyDurationInMinsHb;
	}

	public void setDutyDurationInMinsHb(int dutyDurationInMinsHb) {
		this.dutyDurationInMinsHb = dutyDurationInMinsHb;
	}

	public int getDutyDurationInMinsNonHb() {
		return dutyDurationInMinsNonHb;
	}

	public void setDutyDurationInMinsNonHb(int dutyDurationInMinsNonHb) {
		this.dutyDurationInMinsNonHb = dutyDurationInMinsNonHb;
	}

	public int getNumOfDaysTouched() {
		return numOfDaysTouched;
	}

	public void setNumOfDaysTouched(int numOfDaysTouched) {
		this.numOfDaysTouched = numOfDaysTouched;
	}

	public void incNumOfDaysTouched(int numOfDaysTouched) {
		this.numOfDaysTouched += numOfDaysTouched;
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

	public int getBriefDurationInMinsHb() {
		return briefDurationInMinsHb;
	}

	public void setBriefDurationInMinsHb(int briefDurationInMinsHb) {
		this.briefDurationInMinsHb = briefDurationInMinsHb;
	}

	public int getBriefDurationInMinsNonHb() {
		return briefDurationInMinsNonHb;
	}

	public void setBriefDurationInMinsNonHb(int briefDurationInMinsNonHb) {
		this.briefDurationInMinsNonHb = briefDurationInMinsNonHb;
	}

	public int getDebriefDurationInMins() {
		return debriefDurationInMins;
	}

	public void setDebriefDurationInMins(int debriefDurationInMins) {
		this.debriefDurationInMins = debriefDurationInMins;
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
}
