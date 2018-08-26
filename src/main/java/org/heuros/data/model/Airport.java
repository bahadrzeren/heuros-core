package org.heuros.data.model;

import org.heuros.core.data.base.AbstractModel;

public class Airport extends AbstractModel implements AirportView {

	private String code = null;

	private boolean hb = false;
	private boolean nonHb = true;
	private boolean domestic = false;
	private boolean international = true;

	private boolean dhNotAllowedIfHBDepOrArr = false;
	private boolean critical = false;
	private boolean agDg = false;
	private boolean oneDutyStation = false;
	private boolean acChangeAllowed = false;
	private boolean specialEuroStation = false;
	private boolean layoverAllowed = false;
	private boolean endDutyIfTouches = false;
	private boolean mandatoryFirstLayover = false;
	private int groupId = 0;
	private boolean legConnectionExceptionStation = false;

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

	public boolean isNonHb() {
		return nonHb;
	}

	public void setNonHb(boolean nonHb) {
		this.nonHb = nonHb;
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

	public boolean isOneDutyStation() {
		return oneDutyStation;
	}

	public void setOneDutyStation(boolean oneDutyStation) {
		this.oneDutyStation = oneDutyStation;
	}

	public boolean isAcChangeAllowed() {
		return acChangeAllowed;
	}

	public void setAcChangeAllowed(boolean acChangeAllowed) {
		this.acChangeAllowed = acChangeAllowed;
	}

	public boolean isSpecialEuroStation() {
		return specialEuroStation;
	}

	public void setSpecialEuroStation(boolean specialEuroStation) {
		this.specialEuroStation = specialEuroStation;
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

	public boolean isLegConnectionExceptionStation() {
		return legConnectionExceptionStation;
	}

	public void setLegConnectionExceptionStation(boolean legConnectionExceptionStation) {
		this.legConnectionExceptionStation = legConnectionExceptionStation;
	}
}
