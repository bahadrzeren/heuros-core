package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Processor;
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
	private int numOfBases = 0;
	private DutyRuleContext dutyRuleContext = null;

	private List<Duty> dl = new LinkedList<Duty>();

	public DutyGenerator(DataRepository<Leg> legRepository,
							OneDimIndexInt<Leg> legConnectionIndex,
							int numOfBases,
							DutyRuleContext dutyRuleContext) {
		this.legs = legRepository.getModels();
		this.legConnectionIndex = legConnectionIndex;
		this.numOfBases = numOfBases;
		this.dutyRuleContext = dutyRuleContext;
	}

	@Override
	public List<Duty> proceed() {
		Duty d = Duty.newInstance(this.numOfBases);

		/*
		 * TODO Day start time must be parametric.
		 */
		LocalDateTime dayEnd = this.legs.get(0).getSobt().truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
		int numOfDutiesGenerated = 0;

		int i = 0;
		while (i < this.legs.size()) {
			Leg l = this.legs.get(i);

			if (l.isCover() || l.isDeadheadable()) {
//if (l.getNdx() == 900)
//System.out.println();

				/*
				 * Duty starter check does not need any HB control therefore -1 is used.
				 */
				int bitwiseValidStarter = dutyRuleContext.getStarterCheckerProxy().canBeStarter(l);
				if (bitwiseValidStarter > 0) {

					/*
					 * Duty leg aggregator does not need any HB control therefore -1 is used.
					 */
					dutyRuleContext.getAggregatorProxy().appendFw(d, l);

					/*
					 * TODO Terminator rule!
					 */
					/*
					 * Duty validator does not need any HB control therefore -1 is used.
					 */
					int bitwiseValidTotalizer = bitwiseValidStarter & dutyRuleContext.getFinalCheckerProxy().acceptable(d);
					if (bitwiseValidTotalizer > 0) {
						try {
							d.setValid(bitwiseValidTotalizer);
							d.setValidated(true);
							dl.add((Duty) d.clone());
						} catch (CloneNotSupportedException e) {
							logger.error(e);
							return null;
						}
					}
					/*
					 * Duty extensibility checker does not need any HB control therefore -1 is used.
					 */
					int bitwiseValidExtensible = bitwiseValidStarter & dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d);
					if (bitwiseValidExtensible > 0) {
						try {
							examineDutyFW(d, l, bitwiseValidExtensible);
						} catch (CloneNotSupportedException e) {
							logger.error(e);
							return null;
						}
					}

					/*
					 * Duty leg aggregator does not need any HB control therefore -1 is used.
					 */
					dutyRuleContext.getAggregatorProxy().removeLast(d);
				}
			}

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

    private void examineDutyFW(Duty d, LegView lastLegOfTheDuty, int bitwiseValidationStatus) throws CloneNotSupportedException {

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
                	int bitwiseValidAppendable = bitwiseValidationStatus & dutyRuleContext.getAppendabilityCheckerProxy().isAppendable(d, l, true);
                	if (bitwiseValidAppendable > 0) {

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
                    	dutyRuleContext.getAggregatorProxy().appendFw(d, l);

						/*
						 * Duty validator does not need any HB control therefore -1 is used.
						 */
                    	int bitwiseValidTotalizer = bitwiseValidAppendable & dutyRuleContext.getFinalCheckerProxy().acceptable(d);
	                    if (bitwiseValidTotalizer > 0) {
	                    	d.setValid(bitwiseValidTotalizer);
	                    	d.setValidated(true);
	                    	dl.add((Duty) d.clone());
	                    }

						/*
						 * Duty extensibility checker does not need any HB control therefore -1 is used.
						 */
	                    int bitwiseValidExtensible = bitwiseValidAppendable & dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d);
	                    if (bitwiseValidExtensible > 0) {
                   			examineDutyFW(d, l, bitwiseValidExtensible);
	                    }

						/*
						 * Duty leg aggregator does not need any HB control therefore -1 is used.
						 */
	                    dutyRuleContext.getAggregatorProxy().removeLast(d);
                    }
    			}
            }
        }
    }
}
