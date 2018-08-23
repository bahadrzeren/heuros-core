package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Processor;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyFactory;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.data.repo.LegRepository;
import org.heuros.rule.DutyRuleContext;

public class DutyGenerator implements Processor<Leg, DutyView> {

	private static Logger logger = Logger.getLogger(DutyGenerator.class);

	private List<Leg> legs;
	private DutyFactory dutyFactory;
	private DutyRuleContext dutyRuleContext;

	private List<DutyView> dl = new LinkedList<DutyView>();

	public DutyGenerator setLegRepository(LegRepository legRepository) {
		this.legs = legRepository.getModels();
		return this;
	}

	public DutyGenerator setDutyFactory(DutyFactory dutyFactory) {
		this.dutyFactory = dutyFactory;
		return this;
	}

	public DutyGenerator setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	@Override
	public List<DutyView> proceed() {
		Duty d = this.dutyFactory.generateModel();

		/*
		 * TODO Day start time must be parametric.
		 */
		LocalDateTime dayEnd = this.legs.get(0).getSobt().truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
		int numOfDutiesGenerated = 0;

		int i = 0;
		while (i < this.legs.size()) {
			Leg l = this.legs.get(i);

//			if (!(f.getScheduledOffblockUtc().before(firstDayEnd) && f.getDepAirport()._nonHb())) {
				if (l.isCover() || l.isDeadheadable()) {
					if (dutyRuleContext.getStarterCheckerProxy().canBeStarter(l)) {

						dutyRuleContext.getAggregatorImpl().append(d, l);

						if (dutyRuleContext.getValidatorProxy().isValid(d)) {
							try {
								addDuty((Duty) d.clone());
								if (dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d))
									examineDutyFW(d, l);
							} catch (CloneNotSupportedException e) {
								logger.error(e);
								return null;
							}
						}

						dutyRuleContext.getAggregatorImpl().removeLast(d);
					}
				}
//			}

			if (l.getSobt().isAfter(dayEnd)) {
				/*
				 * TODO Day start time must be parametric.
				 */
				logger.info((dl.size() - numOfDutiesGenerated) + " duties generated until " + dayEnd);
				dayEnd = l.getSobt().truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
				numOfDutiesGenerated = dl.size();
			}

			i++;
		}

		logger.info((dl.size() - numOfDutiesGenerated) + " duties generated until " + dayEnd);
		logger.info("Duty generation is completed and " + dl.size() + " number of duties generated.");

		return this.dl;
	}

    private void examineDutyFW(Duty d, LegView lastLegInDuty) throws CloneNotSupportedException {

//        Leg[] ls = null;
//
//    	fa = SourceStore.getFs_LegConnArr().getArray(lastLegInDuty.ndx);
//
//    	if (fa != null) {
//
//            for (Flight f: fa) {
//
//                if (f.isCover() || f.crpas) {
//                    if (ruleManager._isFlightAddable(d, f)
//                    		&& ruleManager._areFlightsConnectable(lf, f)) {
//
//                    	d.ue(calendar, repo, f, f.isCover());
//
//	                    if (ruleManager._isDutyValid(d)) {
//	                    	if (ruleManager._canDutyEnd(d)
//	                    			&& ruleManager._canADutyEndAt(f)) {
////	                    		if (f.getScheduledOffblockUtc().before(lastDayBegin) || f.getArrAirport()._hb())
//	                    			if (isDutyQualitySufficient(d))
//	                    				addDuty((Duty) d.clone(), f);
//	                    	}
//
//                    		if (ruleManager._canDutyContinue(d))
//                    			examineDutyFW(calendar, d);
//	                    }
//
//	                    d.us(calendar, repo);
//                    }
//    			}
//            }
//        }
    }

	public void addDuty(Duty d) {
		dl.add(d);

		Leg l = null;

        for (int i = 0; i < d.getLegs().size(); i++) {
			l = (Leg) d.getLegs().get(i);
			l.incNumOfDutiesIncludes();
			/*
			 * TODO HB impl will be changed!
			 */
			if (d.getFirstDepAirport().isHb())
				l.incNumOfDutiesIncludesHbDep();
			else
				l.incNumOfDutiesIncludesNonHbDep();
			if (d.getLastArrAirport().isHb())
				l.incNumOfDutiesIncludesHbArr();
			else
				l.incNumOfDutiesIncludesNonHbArr();
        }
    }
}
