package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Processor;
import org.heuros.core.data.base.ModelFactory;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.repo.DataRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.rule.DutyRuleContext;

/**
 * Duty generator.
 * Genetrates list if all valid duties by checking all Leg connections via legConnectionIndex.
 * 
 * @author bahadrzeren
 *
 */
public class DutyGenerator implements Processor<LegView, Duty> {

	private static Logger logger = Logger.getLogger(DutyGenerator.class);

	private List<Leg> legs = null;
	private OneDimIndexInt<Leg> legConnectionIndex = null;
	private ModelFactory<Duty> dutyFactory = null;
	private DutyRuleContext dutyRuleContext = null;

	private List<Duty> dl = new LinkedList<Duty>();

	public DutyGenerator setLegRepository(DataRepository<Leg> legRepository) {
		this.legs = legRepository.getModels();
		return this;
	}

	public DutyGenerator setLegConnectionIndex(OneDimIndexInt<Leg> legConnectionIndex) {
		this.legConnectionIndex = legConnectionIndex;
		return this;
	}

	public DutyGenerator setDutyFactory(ModelFactory<Duty> dutyFactory) {
		this.dutyFactory = dutyFactory;
		return this;
	}

	public DutyGenerator setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	@Override
	public List<Duty> proceed() {
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
					/*
					 * Duty starter check does not need any HB control therefore -1 is used.
					 */
					if (dutyRuleContext.getStarterCheckerProxy().canBeStarter(l, -1)) {

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
						dutyRuleContext.getAggregatorImpl().append(d, l, -1);

						/*
						 * TODO Terminator rule!
						 */
						/*
						 * Duty validator does not need any HB control therefore -1 is used.
						 */
						if (dutyRuleContext.getValidatorProxy().isValid(d, -1)) {
							try {
								dl.add((Duty) d.clone());
							} catch (CloneNotSupportedException e) {
								logger.error(e);
								return null;
							}
						}
						/*
						 * Duty extensibility checker does not need any HB control therefore -1 is used.
						 */
						if (dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d, -1)) {
							try {
								examineDutyFW(d, l);
							} catch (CloneNotSupportedException e) {
								logger.error(e);
								return null;
							}
						}

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
						dutyRuleContext.getAggregatorImpl().removeLast(d, -1);
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

    private void examineDutyFW(Duty d, LegView lastLegOfTheDuty) throws CloneNotSupportedException {

        Leg[] connLs = legConnectionIndex.getArray(lastLegOfTheDuty.getNdx());

    	if (connLs != null) {

            for (Leg l: connLs) {

                if (l.isCover() || l.isDeadheadable()) {
                	/*
                	 * We already checked legs connectibility on LegConnectionIndex generation phase.
                	 */
					/*
					 * Duty leg appendability checker does not need any HB control therefore -1 is used.
					 */
                	if (dutyRuleContext.getAppendabilityCheckerProxy().isAppendable(d, l, -1)) {

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
                    	dutyRuleContext.getAggregatorImpl().append(d, l, -1);

						/*
						 * Duty validator does not need any HB control therefore -1 is used.
						 */
	                    if (dutyRuleContext.getValidatorProxy().isValid(d, -1)) {
	                    	dl.add((Duty) d.clone());
	                    }

						/*
						 * Duty extensibility checker does not need any HB control therefore -1 is used.
						 */
	                    if (dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d, -1)) {
                   			examineDutyFW(d, l);
	                    }

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
	                    dutyRuleContext.getAggregatorImpl().removeLast(d, -1);
                    }
    			}
            }
        }
    }
}
