package org.heuros.data.model;

public class PairHbSpec implements Cloneable {

	private int briefDurationInMins = 0;
	private int dutyDurationInMins = 0;
	private int debriefDurationInMins = 0;
	private int restDurationInMins = 0;
	private int numOfDaysTouched = 0;
	private int numOfEarlyDuties = 0;
	private int numOfHardDuties = 0;
	private int numOfAugmentedDuties = 0;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
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
}
