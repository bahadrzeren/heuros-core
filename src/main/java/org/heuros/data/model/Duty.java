package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.heuros.core.data.base.AbstractModel;

/**
 * Model class for Duty instances.
 * 
 * @author bahadrzeren
 *
 */
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

	private boolean er = false;

	private boolean international = false;

	private Stack<Integer> longestBlockTimesInMins = new Stack<Integer>();

	private int totalNumOfIncludingDutiesOfTheSameLegs = 0;

	private DutyHbSpec[] dutyHbSpecs = null;
	public DutyHbSpec[] getDutyHbSpecs() {
		return dutyHbSpecs;
	}
	public void setDutyHbSpecs(DutyHbSpec[] dutyHbSpec) {
		this.dutyHbSpecs = dutyHbSpec;
	}

	private boolean validated = false;

	private Duty() {
	}

	@SuppressWarnings("unchecked")
	@Override
    public Object clone() throws CloneNotSupportedException {
        Duty d = (Duty) super.clone();
        d.legs = new ArrayList<LegView>(this.legs.size());
        d.dutyHbSpecs = new DutyHbSpec[this.dutyHbSpecs.length];
        for (int i = 0; i < this.legs.size(); i++)
            d.legs.add(this.legs.get(i));
        for (int i = 0; i < this.dutyHbSpecs.length; i++)
        	d.dutyHbSpecs[i] = (DutyHbSpec) this.dutyHbSpecs[i].clone();
        d.longestBlockTimesInMins = (Stack<Integer>) this.longestBlockTimesInMins.clone();
        return d;
    }

	public void appendFw(LegView leg) {
		this.legs.add(leg);
	}
	public void appendBw(LegView leg) {
		this.legs.add(0, leg);
	}
	public LegView removeLast() {
		if (this.numOfLegs > 0)
			return this.legs.remove(this.numOfLegs - 1);
		return null;
	}
	public LegView removeFirst() {
		if (this.numOfLegs > 0)
			return this.legs.remove(0);
		return null;
	}
	@Override
	public LegView getFirstLeg() {
		if (this.numOfLegs > 0)
			return this.legs.get(0);
		return null;
	}
	@Override
	public LegView getLastLeg() {
		if (this.numOfLegs > 0)
			return this.legs.get(this.numOfLegs - 1);
		return null;		
	}
	@Override
	public LegView getSecondToLastLeg() {
		if (this.numOfLegs > 1)
			return this.legs.get(this.numOfLegs - 2);
		return null;		
	}
	@Override
	public LegView getSecondLeg() {
		if (this.numOfLegs > 1)
			return this.legs.get(1);
		return null;		
	}
	@Override
	public AirportView getFirstDepAirport() {
		if (this.numOfLegs > 0)
			return this.legs.get(0).getDepAirport();
		return null;
	}
	@Override
	public AirportView getLastArrAirport() {
		if (this.numOfLegs > 0)
			return this.legs.get(this.numOfLegs - 1).getArrAirport();
		return null;
	}

	public boolean isAnyHbDep() {
		return this.getFirstDepAirport().isAnyHb();
	}
	public boolean isAnyHbArr() {
		return this.getLastArrAirport().isAnyHb();
	}
	public boolean isAnyNonHbDep() {
		return this.getFirstDepAirport().isAnyNonHb();
	}
	public boolean isAnyNonHbArr() {
		return this.getLastArrAirport().isAnyNonHb();
	}
	public boolean isHbDep(int hbNdx) {
		return this.getFirstDepAirport().isHb(hbNdx);
	}
	public boolean isHbArr(int hbNdx) {
		return this.getLastArrAirport().isHb(hbNdx);
	}
	public boolean isNonHbDep(int hbNdx) {
		return this.getFirstDepAirport().isNonHb(hbNdx);
	}
	public boolean isNonHbArr(int hbNdx) {
		return this.getLastArrAirport().isNonHb(hbNdx);
	}

	@Override
	public List<LegView> getLegs() {
		return legs;
	}
	public void setLegs(List<LegView> legs) {
		this.legs = legs;
	}

	@Override
	public int getBlockTimeInMins() {
		return blockTimeInMins;
	}
	public void setBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins = blockTimeInMins;
	}
	public void incBlockTimeInMins(int blockTimeInMins) {
		this.blockTimeInMins += blockTimeInMins;
	}

	@Override
	public int getBlockTimeInMinsActive() {
		return blockTimeInMinsActive;
	}
	public void setBlockTimeInMinsActive(int blockTimeInMinsActive) {
		this.blockTimeInMinsActive = blockTimeInMinsActive;
	}
	public void incBlockTimeInMinsActive(int blockTimeInMinsActive) {
		this.blockTimeInMinsActive += blockTimeInMinsActive;
	}

	@Override
	public int getBlockTimeInMinsPassive() {
		return blockTimeInMinsPassive;
	}
	public void setBlockTimeInMinsPassive(int blockTimeInMinsPassive) {
		this.blockTimeInMinsPassive = blockTimeInMinsPassive;
	}
	public void incBlockTimeInMinsPassive(int blockTimeInMinsPassive) {
		this.blockTimeInMinsPassive += blockTimeInMinsPassive;
	}

	@Override
	public int getNumOfLegs() {
		return numOfLegs;
	}
	public void setNumOfLegs(int numOfLegs) {
		this.numOfLegs = numOfLegs;
	}
	public void incNumOfLegs(int numOfLegs) {
		this.numOfLegs += numOfLegs;
	}

	@Override
	public int getNumOfLegsActive() {
		return numOfLegsActive;
	}
	public void setNumOfLegsActive(int numOfLegsActive) {
		this.numOfLegsActive = numOfLegsActive;
	}
	public void incNumOfLegsActive(int numOfLegsActive) {
		this.numOfLegsActive += numOfLegsActive;
	}

	@Override
	public int getNumOfLegsPassive() {
		return numOfLegsPassive;
	}
	public void setNumOfLegsPassive(int numOfLegsPassive) {
		this.numOfLegsPassive = numOfLegsPassive;
	}
	public void incNumOfLegsPassive(int numOfLegsPassive) {
		this.numOfLegsPassive += numOfLegsPassive;
	}

	@Override
	public int getNumOfLegsIntToDom() {
		return numOfLegsIntToDom;
	}
	public void setNumOfLegsIntToDom(int numOfLegsIntToDom) {
		this.numOfLegsIntToDom = numOfLegsIntToDom;
	}
	public void incNumOfLegsIntToDom(int numOfLegsIntToDom) {
		this.numOfLegsIntToDom += numOfLegsIntToDom;
	}

	@Override
	public int getNumOfLegsDomToInt() {
		return numOfLegsDomToInt;
	}
	public void setNumOfLegsDomToInt(int numOfLegsDomToInt) {
		this.numOfLegsDomToInt = numOfLegsDomToInt;
	}
	public void incNumOfLegsDomToInt(int numOfLegsDomToInt) {
		this.numOfLegsDomToInt += numOfLegsDomToInt;
	}

	@Override
	public int getNumOfCriticalLegs() {
		return numOfCriticalLegs;
	}
	public void setNumOfCriticalLegs(int numOfCriticalLegs) {
		this.numOfCriticalLegs = numOfCriticalLegs;
	}
	public void incNumOfCriticalLegs(int numOfCriticalLegs) {
		this.numOfCriticalLegs += numOfCriticalLegs;
	}

	@Override
	public int getNumOfAgDg() {
		return numOfAgDg;
	}
	public void setNumOfAgDg(int numOfAgDg) {
		this.numOfAgDg = numOfAgDg;
	}
	public void incNumOfAgDg(int numOfAgDg) {
		this.numOfAgDg += numOfAgDg;
	}

	@Override
	public int getNumOfSpecialFlights() {
		return numOfSpecialFlights;
	}
	public void setNumOfSpecialFlights(int numOfSpecialFlights) {
		this.numOfSpecialFlights = numOfSpecialFlights;
	}
	public void incNumOfSpecialFlights(int numOfSpecialFlights) {
		this.numOfSpecialFlights += numOfSpecialFlights;
	}

	@Override
	public int getNumOfAnyHomebaseTouch() {
		return numOfAnyHomebaseTouch;
	}
	public void setNumOfAnyHomebaseTouch(int numOfAnyHomebaseTouch) {
		this.numOfAnyHomebaseTouch = numOfAnyHomebaseTouch;
	}
	public void incNumOfAnyHomebaseTouch(int numOfAnyHomebaseTouch) {
		this.numOfAnyHomebaseTouch += numOfAnyHomebaseTouch;
	}

	@Override
	public int getNumOfDomTouch() {
		return numOfDomTouch;
	}
	public void setNumOfDomTouch(int numOfDomTouch) {
		this.numOfDomTouch = numOfDomTouch;
	}
	public void incNumOfDomTouch(int numOfDomTouch) {
		this.numOfDomTouch += numOfDomTouch;
	}

	@Override
	public int getNumOfIntTouch() {
		return numOfIntTouch;
	}
	public void setNumOfIntTouch(int numOfIntTouch) {
		this.numOfIntTouch = numOfIntTouch;
	}
	public void incNumOfIntTouch(int numOfIntTouch) {
		this.numOfIntTouch += numOfIntTouch;
	}

	@Override
	public int getNumOfAcChanges() {
		return numOfAcChanges;
	}
	public void setNumOfAcChanges(int numOfAcChanges) {
		this.numOfAcChanges = numOfAcChanges;
	}
	public void incNumOfAcChanges(int numOfAcChanges) {
		this.numOfAcChanges += numOfAcChanges;
	}

	@Override
	public int getLongConnDiff() {
		return longConnDiff;
	}
	public void setLongConnDiff(int longConnDiff) {
		this.longConnDiff = longConnDiff;
	}
	public void incLongConnDiff(int longConnDiff) {
		this.longConnDiff += longConnDiff;
	}

	@Override
	public boolean isEr() {
		return er;
	}
	public void setEr(boolean er) {
		this.er = er;
	}

	@Override
	public boolean isInternational() {
		return international;
	}
	public void setInternational(boolean international) {
		this.international = international;
	}


	@Override
	public void setLongestBlockTimeInMins(int longestBlockTimeInMins) {
		this.longestBlockTimesInMins.push(longestBlockTimeInMins);
	}
	@Override
	public int getLongestBlockTimeInMins() {
		return this.longestBlockTimesInMins.peek();
	}
	@Override
	public int removeLongestBlockTimeInMins() {
		return this.longestBlockTimesInMins.pop();
	}
	@Override
	public void removeAllLongestBlockTimeInMins() {
		this.longestBlockTimesInMins.clear();
	}


	@Override
	public int getTotalNumOfIncludingDutiesOfTheSameLegs() {
		return totalNumOfIncludingDutiesOfTheSameLegs;
	}
	public void setTotalNumOfIncludingDutiesOfTheSameLegs(int totalNumOfIncludingDutiesOfTheSameLegs) {
		this.totalNumOfIncludingDutiesOfTheSameLegs = totalNumOfIncludingDutiesOfTheSameLegs;
	}
	public void incTotalNumOfIncludingDutiesOfTheSameLegs(int totalNumOfIncludingDutiesOfTheSameLegs) {
		this.totalNumOfIncludingDutiesOfTheSameLegs += totalNumOfIncludingDutiesOfTheSameLegs;
	}

	@Override
	public int getNumOfHomebaseTouch(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getNumOfHomebaseTouch();
	}
	public void incNumOfHomebaseTouch(int hbNdx, int numOfHomebaseTouch) {
		this.dutyHbSpecs[hbNdx].incNumOfHomebaseTouch(numOfHomebaseTouch);
	}

	@Override
	public int getBriefDurationInMins(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getBriefDurationInMins();
	}
	public void setBriefDurationInMins(int hbNdx, int briefDurationInMins) {
		this.dutyHbSpecs[hbNdx].setBriefDurationInMins(briefDurationInMins);
	}

	@Override
	public LocalDateTime getBriefTime(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getBriefTime();
	}
	public void setBriefTime(int hbNdx, LocalDateTime briefTime) {
		this.dutyHbSpecs[hbNdx].setBriefTime(briefTime);
	}

	@Override
	public LocalDateTime getBriefDayBeginning(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getBriefDayBeginning();
	}
	public void setBriefDayBeginning(int hbNdx, LocalDateTime briefDayBeginning) {
		this.dutyHbSpecs[hbNdx].setBriefDayBeginning(briefDayBeginning);
	}

	@Override
	public LocalDate getBriefDay(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getBriefDay();
	}
	public void setBriefDay(int hbNdx, LocalDate briefDay) {
		this.dutyHbSpecs[hbNdx].setBriefDay(briefDay);
	}

	@Override
	public LocalDateTime getDebriefTime(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getDebriefTime();
	}
	public void setDebriefTime(int hbNdx, LocalDateTime debriefTime) {
		this.dutyHbSpecs[hbNdx].setDebriefTime(debriefTime);
	}

	@Override
	public LocalDateTime getDebriefDayEnding(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getDebriefDayEnding();
	}
	public void setDebriefDayEnding(int hbNdx, LocalDateTime debriefDayEnding) {
		this.dutyHbSpecs[hbNdx].setDebriefDayEnding(debriefDayEnding);
	}

	@Override
	public LocalDate getDebriefDay(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getDebriefDay();
	}
	public void setDebriefDay(int hbNdx, LocalDate debriefDay) {
		this.dutyHbSpecs[hbNdx].setDebriefDay(debriefDay);
	}

	@Override
	public int getDebriefDurationInMins(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getDebriefDurationInMins();
	}
	public void setDebriefDurationInMins(int hbNdx, int debriefDurationInMins) {
		this.dutyHbSpecs[hbNdx].setDebriefDurationInMins(debriefDurationInMins);
	}

	@Override
	public int getDutyDurationInMins(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getDutyDurationInMins();
	}
	public void setDutyDurationInMins(int hbNdx, int dutyDurationInMins) {
		this.dutyHbSpecs[hbNdx].setDutyDurationInMins(dutyDurationInMins);
	}

	@Override
	public int getNumOfDaysTouched(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getNumOfDaysTouched();
	}
	public void setNumOfDaysTouched(int hbNdx, int numOfDaysTouched) {
		this.dutyHbSpecs[hbNdx].setNumOfDaysTouched(numOfDaysTouched);
	}

	@Override
	public int getRestDurationInMins(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getRestDurationInMins();
	}
	public void setRestDurationInMins(int hbNdx, int restDurationInMins) {
		this.dutyHbSpecs[hbNdx].setRestDurationInMins(restDurationInMins);
	}

	@Override
	public LocalDateTime getNextBriefTime(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getNextBriefTime();
	}
	public void setNextBriefTime(int hbNdx, LocalDateTime nextBriefTime) {
		this.dutyHbSpecs[hbNdx].setNextBriefTime(nextBriefTime);
	}

	@Override
	public int getAugmented(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getAugmented();
	}
	public void setAugmented(int hbNdx, int augmented) {
		this.dutyHbSpecs[hbNdx].setAugmented(augmented);
	}

	@Override
	public boolean isEarly(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].isEarly();
	}
	public void setEarly(int hbNdx, boolean early) {
		this.dutyHbSpecs[hbNdx].setEarly(early);
	}

	@Override
	public boolean isHard(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].isHard();
	}
	public void setHard(int hbNdx, boolean hard) {
		this.dutyHbSpecs[hbNdx].setHard(hard);
	}

	@Override
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	@Override
	public boolean isValid(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].isValid();
	}
	public void setValid(int hbNdx, boolean valid) {
		this.dutyHbSpecs[hbNdx].setValid(valid);
	}
	public void setValid(int validationVector) {
		for (int i = 0; i < this.dutyHbSpecs.length; i++)
			this.dutyHbSpecs[i].setValid((validationVector & (1 << i)) > 0);
	}

	@Override
	public boolean hasPairing(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].isHasPair();
	}
	public void setHasPairing(int hbNdx, boolean value) {
		this.dutyHbSpecs[hbNdx].setHasPair(value);
	}

	@Override
	public void setMinNextBriefTime(int hbNdx, LocalDateTime minNextBriefTime) {
		this.dutyHbSpecs[hbNdx].setMinNextBriefTime(minNextBriefTime);
	}
	@Override
	public LocalDateTime getMinNextBriefTime(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getMinNextBriefTime();
	}

	@Override
	public void setMinPrevDebriefTime(int hbNdx, LocalDateTime minPrevDebriefTime) {
		this.dutyHbSpecs[hbNdx].setMinPrevDebriefTime(minPrevDebriefTime);
	}
	@Override
	public LocalDateTime getMinPrevDebriefTime(int hbNdx) {
		return this.dutyHbSpecs[hbNdx].getMinPrevDebriefTime();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(ndx + 
												", ABT:" + this.blockTimeInMinsActive + 
												", PBT:" + this.blockTimeInMinsPassive +
												", A#L:" + this.numOfLegsActive + 
												", P#L:" + this.numOfLegsPassive +
												", S#D:" + this.totalNumOfIncludingDutiesOfTheSameLegs +
												"\n");
		this.legs.forEach((l) -> sb.append(l).append("\n"));
		return sb.toString();
	}

	public static Duty newInstance(int numOfBases) {
		Duty d = new Duty();
		d.setLegs(new ArrayList<LegView>());
		d.setDutyHbSpecs(new DutyHbSpec[numOfBases]);
		for (int i = 0; i < numOfBases; i++) {
			d.getDutyHbSpecs()[i] = new DutyHbSpec();
			d.getDutyHbSpecs()[i].setHbNdx(i);
		}
		return d;
	}
}
