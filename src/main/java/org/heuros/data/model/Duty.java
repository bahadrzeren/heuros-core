package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Duty extends AbstractModel implements DutyView, Cloneable {

	private List<LegView> legs = null;

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
	private int numOfSpecialFlights = 0;

	private int numOfAnyHomebaseTouch = 0;
	private int numOfDomTouch = 0;
	private int numOfIntTouch = 0;

	private int numOfAcChanges = 0;

	private int longConnDiff = 0;

	private LocalDateTime briefTimeHb = null;
	private LocalDateTime briefTimeNonHb = null;
	private LocalDateTime debriefTime = null;

	private LocalDateTime briefDayBeginningHb = null;
	private LocalDateTime briefDayBeginningNonHb = null;
	private LocalDateTime debriefDayEnding = null;

	private LocalDate briefDayHb = null;
	private LocalDate briefDayNonHb = null;
	private LocalDate debriefDay = null;

	private int briefDurationInMinsHb = 0;
	private int briefDurationInMinsNonHb = 0;
	private int debriefDurationInMins = 0;

	private int dutyDurationInMinsHb = 0;
	private int dutyDurationInMinsNonHb = 0;

	private int numOfDaysTouchedHb = 0;
	private int numOfDaysTouchedNonHb = 0;

	private boolean er = false;

	private int restDurationInMinsHbToHb = 0;
	private int restDurationInMinsHbToNonHb = 0;
	private int restDurationInMinsNonHbToHb = 0;
	private int restDurationInMinsNonHbToNonHb = 0;

	private LocalDateTime nextBriefTimeHbToHb = null;
	private LocalDateTime nextBriefTimeHbToNonHb = null;
	private LocalDateTime nextBriefTimeNonHbToHb = null;
	private LocalDateTime nextBriefTimeNonHbToNonHb = null;

	private int augmentedHb = 0;
	private int augmentedNonHb = 0;

	private boolean international = false;
	private boolean earlyHb = false;
	private boolean earlyNonHb = false;
	private boolean hardHb = false;
	private boolean hardNonHb = false;

	private int[] longestBlockTimesInMins = new int[15];
	private int longestBlockTimeInMins = 0;

	private boolean validHb = true;
	private boolean validNonHb = true;

	private int totalNumOfIncludingDutiesOfTheSameLegs = 0;

	@Override
    public Object clone() throws CloneNotSupportedException {
        Duty d = (Duty) super.clone();
        d.legs = new ArrayList<LegView>(this.legs.size());
        for (int i = 0; i < this.legs.size(); i++) {
            d.legs.add(this.legs.get(i));
        }
        d.longestBlockTimesInMins = this.longestBlockTimesInMins.clone();
        return d;
    }

	public void append(LegView leg) {
		this.legs.add(leg);
	}
	public LegView removeLast() {
		if (this.numOfLegs > 0)
			return this.legs.remove(this.numOfLegs - 1);
		return null;
	}
	public LegView getFirstLeg() {
		if (this.numOfLegs > 0)
			return this.legs.get(0);
		return null;
	}
	public LegView getLastLeg() {
		if (this.numOfLegs > 0)
			return this.legs.get(this.numOfLegs - 1);
		return null;		
	}
	public LegView getSecondToLastLeg() {
		if (this.numOfLegs > 1)
			return this.legs.get(this.numOfLegs - 2);
		return null;		
	}
	public AirportView getFirstDepAirport() {
		if (this.numOfLegs > 0)
			return this.legs.get(0).getDepAirport();
		return null;
	}
	public AirportView getLastArrAirport() {
		if (this.numOfLegs > 0)
			return this.legs.get(this.numOfLegs - 1).getArrAirport();
		return null;
	}

	public List<LegView> getLegs() {
		return legs;
	}

	public void setLegs(List<LegView> legs) {
		this.legs = legs;
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

	public int getNumOfSpecialFlights() {
		return numOfSpecialFlights;
	}

	public void setNumOfSpecialFlights(int numOfSpecialFlights) {
		this.numOfSpecialFlights = numOfSpecialFlights;
	}

	public void incNumOfSpecialFlights(int numOfSpecialFlights) {
		this.numOfSpecialFlights += numOfSpecialFlights;
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

	public LocalDate getBriefDayHb() {
		return briefDayHb;
	}

	public void setBriefDayHb(LocalDate briefDayHb) {
		this.briefDayHb = briefDayHb;
	}

	public LocalDate getBriefDayNonHb() {
		return briefDayNonHb;
	}

	public void setBriefDayNonHb(LocalDate briefDayNonHb) {
		this.briefDayNonHb = briefDayNonHb;
	}

	public LocalDate getDebriefDay() {
		return debriefDay;
	}

	public void setDebriefDay(LocalDate debriefDay) {
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

	public int getNumOfDaysTouchedHb() {
		return numOfDaysTouchedHb;
	}

	public void setNumOfDaysTouchedHb(int numOfDaysTouchedHb) {
		this.numOfDaysTouchedHb = numOfDaysTouchedHb;
	}

	public int getNumOfDaysTouchedNonHb() {
		return numOfDaysTouchedNonHb;
	}

	public void setNumOfDaysTouchedNonHb(int numOfDaysTouchedNonHb) {
		this.numOfDaysTouchedNonHb = numOfDaysTouchedNonHb;
	}

	public boolean isEr() {
		return er;
	}

	public void setEr(boolean er) {
		this.er = er;
	}

	public int getRestDurationInMinsHbToHb() {
		return restDurationInMinsHbToHb;
	}
	public void setRestDurationInMinsHbToHb(int restDurationInMinsHbToHb) {
		this.restDurationInMinsHbToHb = restDurationInMinsHbToHb;
	}
	public int getRestDurationInMinsHbToNonHb() {
		return restDurationInMinsHbToNonHb;
	}
	public void setRestDurationInMinsHbToNonHb(int restDurationInMinsHbToNonHb) {
		this.restDurationInMinsHbToNonHb = restDurationInMinsHbToNonHb;
	}
	public int getRestDurationInMinsNonHbToHb() {
		return restDurationInMinsNonHbToHb;
	}
	public void setRestDurationInMinsNonHbToHb(int restDurationInMinsNonHbToHb) {
		this.restDurationInMinsNonHbToHb = restDurationInMinsNonHbToHb;
	}
	public int getRestDurationInMinsNonHbToNonHb() {
		return restDurationInMinsNonHbToNonHb;
	}
	public void setRestDurationInMinsNonHbToNonHb(int restDurationInMinsNonHbToNonHb) {
		this.restDurationInMinsNonHbToNonHb = restDurationInMinsNonHbToNonHb;
	}

	public LocalDateTime getNextBriefTimeHbToHb() {
		return nextBriefTimeHbToHb;
	}
	public void setNextBriefTimeHbToHb(LocalDateTime nextBriefTimeHbToHb) {
		this.nextBriefTimeHbToHb = nextBriefTimeHbToHb;
	}
	public LocalDateTime getNextBriefTimeHbToNonHb() {
		return nextBriefTimeHbToNonHb;
	}
	public void setNextBriefTimeHbToNonHb(LocalDateTime nextBriefTimeHbToNonHb) {
		this.nextBriefTimeHbToNonHb = nextBriefTimeHbToNonHb;
	}
	public LocalDateTime getNextBriefTimeNonHbToHb() {
		return nextBriefTimeNonHbToHb;
	}
	public void setNextBriefTimeNonHbToHb(LocalDateTime nextBriefTimeNonHbToHb) {
		this.nextBriefTimeNonHbToHb = nextBriefTimeNonHbToHb;
	}
	public LocalDateTime getNextBriefTimeNonHbToNonHb() {
		return nextBriefTimeNonHbToNonHb;
	}
	public void setNextBriefTimeNonHbToNonHb(LocalDateTime nextBriefTimeNonHbToNonHb) {
		this.nextBriefTimeNonHbToNonHb = nextBriefTimeNonHbToNonHb;
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

	public boolean isInternational() {
		return international;
	}
	public void setInternational(boolean international) {
		this.international = international;
	}
	public boolean isEarlyHb() {
		return earlyHb;
	}
	public void setEarlyHb(boolean earlyHb) {
		this.earlyHb = earlyHb;
	}
	public boolean isEarlyNonHb() {
		return earlyNonHb;
	}
	public void setEarlyNonHb(boolean earlyNonHb) {
		this.earlyNonHb = earlyNonHb;
	}
	public boolean isHardHb() {
		return hardHb;
	}
	public void setHardHb(boolean hardHb) {
		this.hardHb = hardHb;
	}
	public boolean isHardNonHb() {
		return hardNonHb;
	}
	public void setHardNonHb(boolean hardNonHb) {
		this.hardNonHb = hardNonHb;
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

	public int getTotalNumOfIncludingDutiesOfTheSameLegs() {
		return totalNumOfIncludingDutiesOfTheSameLegs;
	}

	public void setTotalNumOfIncludingDutiesOfTheSameLegs(int totalNumOfIncludingDutiesOfTheSameLegs) {
		this.totalNumOfIncludingDutiesOfTheSameLegs = totalNumOfIncludingDutiesOfTheSameLegs;
	}

	public void incTotalNumOfIncludingDutiesOfTheSameLegs(int totalNumOfIncludingDutiesOfTheSameLegs) {
		this.totalNumOfIncludingDutiesOfTheSameLegs += totalNumOfIncludingDutiesOfTheSameLegs;
	}

	/*
	 * TODO HB
	 */
	@Override
	public int getBriefDurationInMins(Airport hb) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public LocalDateTime getBriefTime(Airport hb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocalDateTime getBriefDayBeginning(Airport hb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LocalDate getBriefDay(Airport hb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDutyDurationInMins(Airport hb) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getNumOfDaysTouched(Airport hb) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRestDurationInMins(Airport hb) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public LocalDateTime getNextBriefTimeHbTo(Airport hb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getAugmented(Airport hb) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEarly(Airport hb) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isHard(Airport hb) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isValid(Airport hb) {
		// TODO Auto-generated method stub
		return false;
	}
}
