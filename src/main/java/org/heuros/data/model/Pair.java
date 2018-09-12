package org.heuros.data.model;

import java.util.List;

import org.heuros.core.data.base.AbstractModel;

/**
 * Model class for Pair instances.
 * 
 * @author bahadrzeren
 *
 */
public class Pair extends AbstractModel implements PairView {
	
	private List<DutyView> duties = null;

	private int blockTimeInMins = 0;
	private int blockTimeInMinsActive = 0;
	private int blockTimeInMinsPassive = 0;
	private int numOfLegs = 0;
	private int numOfLegsActive = 0;
	private int numOfLegsPassive = 0;
	private int numOfLegsIntToDom = 0;
	private int numOfLegsDomToInt = 0;
	private int numOfDuties = 0;
	private int numOfInternationalDuties = 0;
	private int numOfErDuties = 0;

	private PairHbSpec[] pairHbSpecs = null;
	public PairHbSpec[] getPairHbSpecs() {
		return pairHbSpecs;
	}
	public void setPairHbSpecs(PairHbSpec[] pairHbSpecs) {
		this.pairHbSpecs = pairHbSpecs;
	}

	@Override
	public boolean isComplete() {
		return this.getFirstDepAirport().isAnyHb()
				&& (this.getFirstDepAirport().getHbNdx() == this.getLastArrAirport().getHbNdx());
	}
	@Override
	public int getHbNdx() {
		if (this.isComplete())
			return this.getFirstDepAirport().getHbNdx();
		return -1;
	}
	@Override
	public int getDepHbNdx() {
		if (this.getFirstDepAirport().isAnyHb())
			return this.getFirstDepAirport().getHbNdx();
		return -1;
	}
	@Override
	public int getArrHbNdx() {
		if (this.getLastArrAirport().isAnyHb())
			return this.getLastArrAirport().getHbNdx();
		return -1;
	}
	@Override
	public boolean isHbDep(int hbNdx) {
		return this.getFirstDepAirport().isAnyHb() && (this.getFirstDepAirport().getHbNdx() == hbNdx); 
	}
	@Override
	public boolean isHbArr(int hbNdx) {
		return this.getLastArrAirport().isAnyHb() && (this.getLastArrAirport().getHbNdx() == hbNdx);
	}

	public void append(DutyView dutyView) {
		this.duties.add(dutyView);
	}
	public DutyView removeLast() {
		if (this.numOfDuties > 0)
			return this.duties.remove(this.numOfDuties - 1);
		return null;
	}
	@Override
	public DutyView getFirstDuty() {
		if (this.numOfDuties > 0)
			return this.duties.get(0);
		return null;
	}
	@Override
	public DutyView getLastDuty() {
		if (this.numOfDuties > 0)
			return this.duties.get(this.numOfDuties - 1);
		return null;		
	}
	@Override
	public LegView getFirstLeg() {
		if (this.numOfDuties > 0)
			return getFirstDuty().getFirstLeg();
		return null;
	}
	@Override
	public LegView getLastLeg() {
		if (this.numOfDuties > 0)
			return getLastDuty().getLastLeg();
		return null;
	}
	@Override
	public AirportView getFirstDepAirport() {
		if (this.numOfDuties > 0)
			return getFirstDuty().getFirstDepAirport();
		return null;
	}
	@Override
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
	public int getBriefDurationInMins(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getBriefDurationInMins();
	}
	public void setBriefDurationInMins(int hbNdx, int briefDurationInMins) {
		this.pairHbSpecs[hbNdx].setBriefDurationInMins(briefDurationInMins);
	}
	public void incBriefDurationInMins(int hbNdx, int briefDurationInMins) {
		this.pairHbSpecs[hbNdx].incBriefDurationInMins(briefDurationInMins);
	}
	@Override
	public int getDutyDurationInMins(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getDutyDurationInMins();
	}
	public void setDutyDurationInMins(int hbNdx, int dutyDurationInMins) {
		this.pairHbSpecs[hbNdx].setDutyDurationInMins(dutyDurationInMins);
	}
	public void incDutyDurationInMins(int hbNdx, int dutyDurationInMins) {
		this.pairHbSpecs[hbNdx].incDutyDurationInMins(dutyDurationInMins);
	}
	@Override
	public int getDebriefDurationInMins(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getDebriefDurationInMins();
	}
	public void setDebriefDurationInMins(int hbNdx, int debriefDurationInMins) {
		this.pairHbSpecs[hbNdx].setDebriefDurationInMins(debriefDurationInMins);
	}
	public void incDebriefDurationInMins(int hbNdx, int debriefDurationInMins) {
		this.pairHbSpecs[hbNdx].incDebriefDurationInMins(debriefDurationInMins);
	}
	@Override
	public int getRestDurationInMins(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getRestDurationInMins();
	}
	public void setRestDurationInMins(int hbNdx, int restDurationInMins) {
		this.pairHbSpecs[hbNdx].setRestDurationInMins(restDurationInMins);
	}
	public void incRestDurationInMins(int hbNdx, int restDurationInMins) {
		this.pairHbSpecs[hbNdx].incRestDurationInMins(restDurationInMins);
	}
	@Override
	public int getNumOfDaysTouched(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getNumOfDaysTouched();
	}
	public void setNumOfDaysTouched(int hbNdx, int numOfDaysTouched) {
		this.pairHbSpecs[hbNdx].setNumOfDaysTouched(numOfDaysTouched);
	}
	public void incNumOfDaysTouched(int hbNdx, int numOfDaysTouched) {
		this.pairHbSpecs[hbNdx].incNumOfDaysTouched(numOfDaysTouched);
	}
	@Override
	public int getNumOfDuties() {
		return numOfDuties;
	}
	public void setNumOfDuties(int numOfDuties) {
		this.numOfDuties = numOfDuties;
	}
	public void incNumOfDuties(int numOfDuties) {
		this.numOfDuties += numOfDuties;
	}
	@Override
	public int getNumOfInternationalDuties() {
		return numOfInternationalDuties;
	}
	public void setNumOfInternationalDuties(int numOfInternationalDuties) {
		this.numOfInternationalDuties = numOfInternationalDuties;
	}
	public void incNumOfInternationalDuties(int numOfInternationalDuties) {
		this.numOfInternationalDuties += numOfInternationalDuties;
	}
	@Override
	public int getNumOfEarlyDuties(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getNumOfEarlyDuties();
	}
	public void setNumOfEarlyDuties(int hbNdx, int numOfEarlyDuties) {
		this.pairHbSpecs[hbNdx].setNumOfEarlyDuties(numOfEarlyDuties);
	}
	public void incNumOfEarlyDuties(int hbNdx, int numOfEarlyDuties) {
		this.pairHbSpecs[hbNdx].incNumOfEarlyDuties(numOfEarlyDuties);
	}
	@Override
	public int getNumOfHardDuties(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getNumOfHardDuties();
	}
	public void setNumOfHardDuties(int hbNdx, int numOfHardDuties) {
		this.pairHbSpecs[hbNdx].setNumOfHardDuties(numOfHardDuties);
	}
	public void incNumOfHardDuties(int hbNdx, int numOfHardDuties) {
		this.pairHbSpecs[hbNdx].incNumOfHardDuties(numOfHardDuties);
	}
	@Override
	public int getNumOfAugmentedDuties(int hbNdx) {
		return this.pairHbSpecs[hbNdx].getNumOfAugmentedDuties();
	}
	public void setNumOfAugmentedDuties(int hbNdx, int numOfAugmentedDuties) {
		this.pairHbSpecs[hbNdx].setNumOfAugmentedDuties(numOfAugmentedDuties);
	}
	public void incNumOfAugmentedDuties(int hbNdx, int numOfAugmentedDuties) {
		this.pairHbSpecs[hbNdx].incNumOfAugmentedDuties(numOfAugmentedDuties);
	}
	@Override
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
