package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Pair extends AbstractModel implements PairView {
	
	private List<DutyView> duties = null;

	private AirportView homeBase = null;
	private int blockTimeInMins = 0;
	private int blockTimeInMinsActive = 0;
	private int blockTimeInMinsPassive = 0;
	private int numOfLegs = 0;
	private int numOfLegsActive = 0;
	private int numOfLegsPassive = 0;
	private int numOfLegsIntToDom = 0;
	private int numOfLegsDomToInt = 0;
	private int briefDurationInMins = 0;
	private int dutyDurationInMins = 0;
	private int debriefDurationInMins = 0;
	private int restDurationInMins = 0;
	private int numOfDaysTouched = 0;
	private int numOfDuties = 0;
	private int numOfInternationalDuties = 0;
	private int numOfEarlyDuties = 0;
	private int numOfHardDuties = 0;
	private int numOfAugmentedDuties = 0;
	private int numOfErDuties = 0;

	public void append(DutyView dutyView) {
		this.duties.add(dutyView);
	}
	public DutyView removeLast() {
		if (this.numOfDuties > 0)
			return this.duties.remove(this.numOfDuties - 1);
		return null;
	}
	public DutyView getFirstDuty() {
		if (this.numOfDuties > 0)
			return this.duties.get(0);
		return null;
	}
	public DutyView getLastDuty() {
		if (this.numOfDuties > 0)
			return this.duties.get(this.numOfDuties - 1);
		return null;		
	}
	public LegView getFirstLeg() {
		if (this.numOfDuties > 0)
			return getFirstDuty().getFirstLeg();
		return null;
	}
	public LegView getLastLeg() {
		if (this.numOfDuties > 0)
			return getLastDuty().getLastLeg();
		return null;
	}
	public AirportView getFirstDepAirport() {
		if (this.numOfDuties > 0)
			return getFirstDuty().getFirstDepAirport();
		return null;
	}
	public AirportView getLastArrAirport() {
		if (this.numOfDuties > 0)
			return getLastDuty().getLastArrAirport();
		return null;
	}

	public List<DutyView> getDuties() {
		return duties;
	}

	public void setDuties(List<DutyView> duties) {
		this.duties = duties;
	}

	public AirportView getHomeBase() {
		return homeBase;
	}
	public void setHomeBase(AirportView homeBase) {
		this.homeBase = homeBase;
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
	public int getBriefDurationInMins() {
		return briefDurationInMins;
	}
	public void setBriefDurationInMins(int briefDurationInMins) {
		this.briefDurationInMins = briefDurationInMins;
	}
	public void incBriefDurationInMins(int briefDurationInMins) {
		this.briefDurationInMins += briefDurationInMins;
	}
	public int getDutyDurationInMins() {
		return dutyDurationInMins;
	}
	public void setDutyDurationInMins(int dutyDurationInMins) {
		this.dutyDurationInMins = dutyDurationInMins;
	}
	public void incDutyDurationInMins(int dutyDurationInMins) {
		this.dutyDurationInMins += dutyDurationInMins;
	}
	public int getDebriefDurationInMins() {
		return debriefDurationInMins;
	}
	public void setDebriefDurationInMins(int debriefDurationInMins) {
		this.debriefDurationInMins = debriefDurationInMins;
	}
	public void incDebriefDurationInMins(int debriefDurationInMins) {
		this.debriefDurationInMins += debriefDurationInMins;
	}
	public int getRestDurationInMins() {
		return restDurationInMins;
	}
	public void setRestDurationInMins(int restDurationInMins) {
		this.restDurationInMins = restDurationInMins;
	}
	public void incRestDurationInMins(int restDurationInMins) {
		this.restDurationInMins += restDurationInMins;
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
	public int getNumOfDuties() {
		return numOfDuties;
	}
	public void setNumOfDuties(int numOfDuties) {
		this.numOfDuties = numOfDuties;
	}
	public void incNumOfDuties(int numOfDuties) {
		this.numOfDuties += numOfDuties;
	}
	public int getNumOfInternationalDuties() {
		return numOfInternationalDuties;
	}
	public void setNumOfInternationalDuties(int numOfInternationalDuties) {
		this.numOfInternationalDuties = numOfInternationalDuties;
	}
	public void incNumOfInternationalDuties(int numOfInternationalDuties) {
		this.numOfInternationalDuties += numOfInternationalDuties;
	}
	public int getNumOfEarlyDuties() {
		return numOfEarlyDuties;
	}
	public void setNumOfEarlyDuties(int numOfEarlyDuties) {
		this.numOfEarlyDuties = numOfEarlyDuties;
	}
	public void incNumOfEarlyDuties(int numOfEarlyDuties) {
		this.numOfEarlyDuties += numOfEarlyDuties;
	}
	public int getNumOfHardDuties() {
		return numOfHardDuties;
	}
	public void setNumOfHardDuties(int numOfHardDuties) {
		this.numOfHardDuties = numOfHardDuties;
	}
	public void incNumOfHardDuties(int numOfHardDuties) {
		this.numOfHardDuties += numOfHardDuties;
	}
	public int getNumOfAugmentedDuties() {
		return numOfAugmentedDuties;
	}
	public void setNumOfAugmentedDuties(int numOfAugmentedDuties) {
		this.numOfAugmentedDuties = numOfAugmentedDuties;
	}
	public void incNumOfAugmentedDuties(int numOfAugmentedDuties) {
		this.numOfAugmentedDuties += numOfAugmentedDuties;
	}
	public int getNumOfErDuties() {
		return numOfErDuties;
	}
	public void setNumOfErDuties(int numOfErDuties) {
		this.numOfErDuties = numOfErDuties;
	}
	public void incNumOfErDuties(int numOfErDuties) {
		this.numOfErDuties += numOfErDuties;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.duties.forEach((d) -> sb.append(d).append("\n"));
		return sb.toString();
	}
}
