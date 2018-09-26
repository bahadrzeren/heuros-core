package org.heuros.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DutyHbSpec implements Cloneable {
	private int hbNdx = -1;
	private int numOfHomebaseTouch = 0;
	private int briefDurationInMins = 0;
	private LocalDateTime briefTime = null;
	private LocalDateTime briefDayBeginning = null;
	private LocalDate briefDay = null;
	private int debriefDurationInMins = 0;
	private LocalDateTime debriefTime = null;
	private LocalDateTime debriefDayEnding = null;
	private LocalDate debriefDay = null;
	private int dutyDurationInMins = 0;
	private int numOfDaysTouched = 0;
	private int restDurationInMins = 0;
	private LocalDateTime nextBriefTime = null;
	private int augmented = 0;
	private boolean early = false;
	private boolean hard = false;

	private boolean valid = false;

	private boolean hasPair = false;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getHbNdx() {
		return hbNdx;
	}
	public void setHbNdx(int hbNdx) {
		this.hbNdx = hbNdx;
	}
	public int getNumOfHomebaseTouch() {
		return numOfHomebaseTouch;
	}
	public void setNumOfHomebaseTouch(int numOfHomebaseTouch) {
		this.numOfHomebaseTouch = numOfHomebaseTouch;
	}
	public void incNumOfHomebaseTouch(int numOfHomebaseTouch) {
		this.numOfHomebaseTouch += numOfHomebaseTouch;
	}
	public int getBriefDurationInMins() {
		return briefDurationInMins;
	}
	public void setBriefDurationInMins(int briefDurationInMins) {
		this.briefDurationInMins = briefDurationInMins;
	}
	public LocalDateTime getBriefTime() {
		return briefTime;
	}
	public void setBriefTime(LocalDateTime briefTime) {
		this.briefTime = briefTime;
	}
	public LocalDateTime getBriefDayBeginning() {
		return briefDayBeginning;
	}
	public void setBriefDayBeginning(LocalDateTime briefDayBeginning) {
		this.briefDayBeginning = briefDayBeginning;
	}
	public LocalDate getBriefDay() {
		return briefDay;
	}
	public void setBriefDay(LocalDate briefDay) {
		this.briefDay = briefDay;
	}
	public int getDebriefDurationInMins() {
		return debriefDurationInMins;
	}
	public void setDebriefDurationInMins(int debriefDurationInMins) {
		this.debriefDurationInMins = debriefDurationInMins;
	}
	public LocalDateTime getDebriefTime() {
		return debriefTime;
	}
	public void setDebriefTime(LocalDateTime debriefTime) {
		this.debriefTime = debriefTime;
	}
	public LocalDateTime getDebriefDayEnding() {
		return debriefDayEnding;
	}
	public void setDebriefDayEnding(LocalDateTime debriefDayEnding) {
		this.debriefDayEnding = debriefDayEnding;
	}
	public LocalDate getDebriefDay() {
		return debriefDay;
	}
	public void setDebriefDay(LocalDate debriefDay) {
		this.debriefDay = debriefDay;
	}
	public int getDutyDurationInMins() {
		return dutyDurationInMins;
	}
	public void setDutyDurationInMins(int dutyDurationInMins) {
		this.dutyDurationInMins = dutyDurationInMins;
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
	public int getRestDurationInMins() {
		return restDurationInMins;
	}
	public void setRestDurationInMins(int restDurationInMins) {
		this.restDurationInMins = restDurationInMins;
	}
	public LocalDateTime getNextBriefTime() {
		return nextBriefTime;
	}
	public void setNextBriefTime(LocalDateTime nextBriefTime) {
		this.nextBriefTime = nextBriefTime;
	}
	public int getAugmented() {
		return augmented;
	}
	public void setAugmented(int augmented) {
		this.augmented = augmented;
	}
	public boolean isEarly() {
		return early;
	}
	public void setEarly(boolean early) {
		this.early = early;
	}
	public boolean isHard() {
		return hard;
	}
	public void setHard(boolean hard) {
		this.hard = hard;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isHasPair() {
		return hasPair;
	}
	public void setHasPair(boolean hasPair) {
		this.hasPair = hasPair;
	}
}
