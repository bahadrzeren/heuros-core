package org.heuros.data.model;

import java.time.LocalDateTime;
import java.util.List;

import org.heuros.core.data.base.AbstractModel;

public class Duty extends AbstractModel implements DutyView {

	private List<DutyLeg> dutyLegs;

	private int _augmHb = 0;
	private int _augmNonHb = 0;
	private boolean _erHb = false;
	private boolean _erNonHb = false;
	private int _restDurationHb = 0;
	private int _restDurationNonHb = 0;

	private int _totDutyDurationHb = 0;
	private int _totDutyDurationNonHb = 0;

	private int _totBlockTime = 0;
	private int _totBlockTimeActive = 0;
	private int _totBlockTimePassive = 0;

	private int _totNumOfLegs = 0;
	private int _totNumOfLegsActive = 0;
	private int _totNumOfLegsPassive = 0;

	private int _totNumOfDaysTouched = 0;
	private int _totNumOfLegsIntToDom = 0;
	private int _totNumOfLegsDomToInt = 0;

	private int[] longestBlockTimes = new int[10];
	private int _longestBlockTime = 0;

	private int _numOfAnyHomebaseTouch = 0;
	private int _numOfDomTouch = 0;
	private int _numOfIntTouch = 0;

//	private boolean _validHb = true;
//	private boolean _validNonHb = true;

	private LocalDateTime _briefTimeHb = null;
	private LocalDateTime _briefTimeNonHb = null;
	private LocalDateTime _debriefTime = null;

	private LocalDateTime _briefDayBeginningHb = null;
	private LocalDateTime _briefDayBeginningNonHb = null;
	private LocalDateTime _debriefDayEnding = null;

	private int _briefDurationHb = 0;
	private int _briefDurationNonHb = 0;
	private int _debriefDuration = 0;

	private LocalDateTime _nextBriefTimeHb = null;
	private LocalDateTime _nextBriefTimeNonHb = null;

	private boolean _isHard = false;
	private boolean _isEarly = false;

	private int _briefDayHb = 0;
	private int _briefDayNonHb = 0;
	private int _debriefDay = 0;

	private int numOfCritLegs = 0;
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
}
