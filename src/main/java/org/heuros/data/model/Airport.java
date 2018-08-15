package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModel;

public class Airport extends AbstractModel implements AirportView {

	private String code;

	private boolean hb = false;
	private boolean nonHB = true;
	private boolean domestic = false;
	private boolean international = true;

	private boolean dhNotAllowedIfHBDepOrArr = false;
	private boolean critical = false;
	private boolean agDg = false;
	private boolean oneDutyStat = false;
	private boolean acChangeAllowed = false;
	private boolean specEuroStat = false;
	private boolean layoverAllowed = false;
	private boolean endDutyIfTouches = false;
	private boolean mandatoryFirstLayover = false;
	private int groupId = 0;
	private boolean legConnExcStation = false;
	private boolean haccStat = false;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isTheSame(Airport ap) {
		return this.getNdx() == ap.getNdx();
	}

	public boolean isHb() {
		return hb;
	}

	public void setHb(boolean hb) {
		this.hb = hb;
	}

	public boolean isNonHB() {
		return nonHB;
	}

	public void setNonHB(boolean nonHB) {
		this.nonHB = nonHB;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}

	public boolean isInternational() {
		return international;
	}

	public void setInternational(boolean international) {
		this.international = international;
	}



	public boolean isDhNotAllowedIfHBDepOrArr() {
		return dhNotAllowedIfHBDepOrArr;
	}

	public void setDhNotAllowedIfHBDepOrArr(boolean dhAlowedIfHBDepOrArr) {
		this.dhNotAllowedIfHBDepOrArr = dhAlowedIfHBDepOrArr;
	}

	public boolean isCritical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	public boolean isAgDg() {
		return agDg;
	}

	public void setAgDg(boolean agDg) {
		this.agDg = agDg;
	}

	public boolean isOneDutyStat() {
		return oneDutyStat;
	}

	public void setOneDutyStat(boolean oneDutyStat) {
		this.oneDutyStat = oneDutyStat;
	}

	public boolean isAcChangeAllowed() {
		return acChangeAllowed;
	}

	public void setAcChangeAllowed(boolean acChangeAllowed) {
		this.acChangeAllowed = acChangeAllowed;
	}

	public boolean isSpecEuroStat() {
		return specEuroStat;
	}

	public void setSpecEuroStat(boolean specEuroStat) {
		this.specEuroStat = specEuroStat;
	}

	public boolean isLayoverAllowed() {
		return layoverAllowed;
	}

	public void setLayoverAllowed(boolean layoverAllowed) {
		this.layoverAllowed = layoverAllowed;
	}

	public boolean isEndDutyIfTouches() {
		return endDutyIfTouches;
	}

	public void setEndDutyIfTouches(boolean endDutyIfTouches) {
		this.endDutyIfTouches = endDutyIfTouches;
	}

	public boolean isMandatoryFirstLayover() {
		return mandatoryFirstLayover;
	}

	public void setMandatoryFirstLayover(boolean mandatoryFirstLayover) {
		this.mandatoryFirstLayover = mandatoryFirstLayover;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isLegConnExcStation() {
		return legConnExcStation;
	}

	public void setLegConnExcStation(boolean legConnExcStation) {
		this.legConnExcStation = legConnExcStation;
	}

	public boolean isHaccStat() {
		return haccStat;
	}

	public void setHaccStat(boolean haccStat) {
		this.haccStat = haccStat;
	}
}
