package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.data.repo.DataRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.Leg;
import org.heuros.data.model.Pair;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class BiDirDutyPairingChecker implements Callable<Boolean> {

	private static Logger logger = Logger.getLogger(BiDirDutyPairingChecker.class);

	private int hbNdx = -1;
	private LocalDateTime dutyProcessPeriodEndExc = null;
	private int effectiveDutyBlockHourLimit = 0;

	public BiDirDutyPairingChecker(int hbNdx,
									LocalDateTime dutyProcessPeriodEndExc,
									int effectiveDutyBlockHourLimit) {
		this.hbNdx = hbNdx;
		this.dutyProcessPeriodEndExc = dutyProcessPeriodEndExc;
		this.effectiveDutyBlockHourLimit = effectiveDutyBlockHourLimit;
	}

	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInHours = 0;
	private List<Duty> duties = null;
	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime= null;

	public BiDirDutyPairingChecker setMaxIdleTimeInAPairInHours(int maxIdleTimeInAPairInHours) {
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		return this;
	}

	public BiDirDutyPairingChecker setMaxPairingLengthInHours(int maxPairingLengthInHours) {
		this.maxPairingLengthInHours = maxPairingLengthInHours;
		return this;
	}

	public BiDirDutyPairingChecker setDutyRepository(DataRepository<Duty> dutyRepository) {
		this.duties = dutyRepository.getModels();
		return this;
	}

	public BiDirDutyPairingChecker setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	public BiDirDutyPairingChecker setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	public BiDirDutyPairingChecker setDutyIndexByDepAirportNdxBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
		return this;
	}

	public BiDirDutyPairingChecker setDutyIndexByArrAirportNdxNextBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime) {
		this.dutyIndexByArrAirportNdxNextBrieftime = dutyIndexByArrAirportNdxNextBrieftime;
		return this;
	}

	private void setHasPairingFlag(Pair p) {
		if (!p.isComplete(this.hbNdx))
			logger.error("Pairing must be complete!");
		int apNdx = p.getFirstDepAirport().getNdx();
		for (int i = 0; i < p.getNumOfDuties(); i++) {
			Duty d = p.getDuties().get(i);
			if (apNdx != d.getFirstDepAirport().getNdx())
				logger.error("Invalid spatial connection exception!");
			apNdx = d.getLastArrAirport().getNdx();
			d.setHasPairing(this.hbNdx, true);
//			for (int j = 0; j < d.getNumOfLegs(); j++) {
//				LegView lv = d.getLegs().get(j);
//				Leg l = this.legs.get(lv.getNdx());
//				if (p.getNumOfDuties() == 1) {
//					l.setHasHbDepArrDutyPair(this.hbNdx, true);
//				} else
//					if (i == 0) {
//						l.setHasHbDepDutyPair(this.hbNdx, true);
//					} else
//						if (i == p.getNumOfDuties() - 1) {
//							l.setHasHbArrDutyPair(this.hbNdx, true);
//						} else
//							l.setHasNonHbDutyPair(this.hbNdx, true);
//			}
		}
	}

	@Override
	public Boolean call() {

		logger.info("Duty pairing generation check is started!");

		Pair p = Pair.newInstance(this.hbNdx);

    	for (int di = 0; di < this.duties.size(); di++) {
    		Duty d = this.duties.get(di);

    		if (d.isValid(this.hbNdx)
    				&& (!d.hasPairing(this.hbNdx))
    				&& d.getFirstLeg().getSobt().isBefore(this.dutyProcessPeriodEndExc)) {
				/*
				 * HB dep and arr.
				 */
	    		if (d.isHbDep(this.hbNdx)
	    				&& d.isHbArr(this.hbNdx)) {
		    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
			    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
			    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
			    			if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
			    				if (p.isComplete(this.hbNdx)) {
			    					/*
			    					 * Set related leg flags!
			    					 */
			    					this.setHasPairingFlag(p);
			    				} else
			    					logger.error("Pairing " + d + " must be complete!");
			    			}
			    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
			    		}
		    		}	    			
	    		} else
					/*
					 * HB dep only.
					 */
		    		if (d.isHbDep(this.hbNdx)
		    				&& d.isNonHbArr(this.hbNdx)) {
			    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
				    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
				    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
				    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    				if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 1)) {
				    				} else
				    					if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 2)) {
					    				} else
					    					if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 3)) {
					    					}
				    			}
				    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
				    		}
			    		}
			    	} else
    					/*
    					 * Non HB dep and arr.
    					 */
			    		if (d.isNonHbDep(this.hbNdx)
			    				&& d.isNonHbArr(this.hbNdx)) {
				    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
				    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
				    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, false, 2)) {
				    				} else
					    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, false, 3)) {
					    				}
				    			}
				    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
				    		}
				    	} else
	    					/*
	    					 * HB arr only.
	    					 */
				    		if (d.isNonHbDep(this.hbNdx)
				    				&& d.isHbArr(this.hbNdx)) {
					    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
					    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
					    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 1)) {
					    				} else
						    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 2)) {
						    				} else
							    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 3)) {
							    				}
					    			}
					    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
					    		}						    			
				    		}
			}

//if ((l.getNdx() == 1017) && ((pairingFound & (1 << 2)) > 0))
//System.out.println(pairingFound);

//    		logger.info(d.getNdx() + " - " + d.hasPairing(this.hbNdx));
		}

    	logger.info("Duty pairing generation check finished!");

    	/*
    	 * Set leg accumulators for legs.
    	 */
		this.duties.forEach((d) -> {
			if (d.hasPairing(hbNdx)
					&& d.isValid(hbNdx)) {
				d.getLegs().forEach((l) -> {
					if (l.isCover()) {
						l.incNumOfIncludingDuties();
						if (d.getNumOfLegsPassive() == 0)
							l.incNumOfIncludingDutiesWoDh();

						/*
						 * If blocktime is bigger than effectiveDutyBlockHourLimit consider it as effective duty.
						 */
						if (d.getBlockTimeInMinsActive() >= this.effectiveDutyBlockHourLimit) {
							l.incNumOfIncludingEffectiveDuties();
							if (d.getNumOfLegsPassive() == 0)
								l.incNumOfIncludingEffectiveDutiesWoDh();
						}

//						if (d.getFirstDepAirport().isHb(hbNdx)) {
//							l.incNumOfIncludingHbDepDuties(hbNdx);
//						} else {
//							l.incNumOfIncludingNonHbDepDuties(hbNdx);
//						}
//						if (d.getLastArrAirport().isHb(hbNdx)) {
//							l.incNumOfIncludingHbArrDuties(hbNdx);
//						} else {
//							l.incNumOfIncludingNonHbArrDuties(hbNdx);
//						}
					}
				});
			}
		});
		/*
		 * Set leg accumulators for duties.
		 */
		duties.forEach((d) -> {
			d.getLegs().forEach((l) -> {
				if (l.isCover()) {
					d.incTotalNumOfAlternativeDuties(l.getNumOfIncludingDuties());
					d.incTotalNumOfAlternativeDutiesWoDh(l.getNumOfIncludingDutiesWoDh());
					if (d.getMinNumOfAlternativeDuties() > l.getNumOfIncludingDuties())
						d.setMinNumOfAlternativeDuties(l.getNumOfIncludingDuties());
					if (d.getMinNumOfAlternativeDutiesWoDh() > l.getNumOfIncludingDutiesWoDh())
						d.setMinNumOfAlternativeDutiesWoDh(l.getNumOfIncludingDutiesWoDh());
//					if (d.getMaxNumOfAlternativeDuties() < l.getNumOfIncludingDuties())
//						d.setMaxNumOfAlternativeDuties(l.getNumOfIncludingDuties());
//					if (d.getMaxNumOfAlternativeDutiesWoDh() < l.getNumOfIncludingDutiesWoDh())
//						d.setMaxNumOfAlternativeDutiesWoDh(l.getNumOfIncludingDutiesWoDh());

					d.incTotalNumOfAlternativeEffectiveDuties(l.getNumOfIncludingEffectiveDuties());
					d.incTotalNumOfAlternativeEffectiveDutiesWoDh(l.getNumOfIncludingEffectiveDutiesWoDh());
					if (d.getMinNumOfAlternativeEffectiveDuties() > l.getNumOfIncludingEffectiveDuties())
						d.setMinNumOfAlternativeEffectiveDuties(l.getNumOfIncludingEffectiveDuties());
					if (d.getMinNumOfAlternativeEffectiveDutiesWoDh() > l.getNumOfIncludingEffectiveDutiesWoDh())
						d.setMinNumOfAlternativeEffectiveDutiesWoDh(l.getNumOfIncludingEffectiveDutiesWoDh());
//					if (d.getMaxNumOfAlternativeEffectiveDuties() < l.getNumOfIncludingEffectiveDuties())
//						d.setMaxNumOfAlternativeEffectiveDuties(l.getNumOfIncludingEffectiveDuties());
//					if (d.getMaxNumOfAlternativeEffectiveDutiesWoDh() < l.getNumOfIncludingEffectiveDutiesWoDh())
//						d.setMaxNumOfAlternativeEffectiveDutiesWoDh(l.getNumOfIncludingEffectiveDutiesWoDh());
				}
			});
		});

		return true;
	}

	private boolean examinePairFW(Pair p, Duty fd, Duty ld, Leg fl, Leg ll, boolean hbDep, boolean hbArr, int dept) {

		if (hbArr)
			logger.error("Invalid search direction!");
		if (dept < 1)
			logger.error("Lack of dept!");

    	boolean pairingFound = false;

		Duty[] nextDuties = null;

		int lastAirportNdx = ll.getArrAirport().getNdx();

		int hourCounter = 0;
		LocalDateTime nextBriefTime = ld.getNextBriefTime(this.hbNdx);

		while (true) {
			nextDuties = this.dutyIndexByDepAirportNdxBrieftime.getArray(lastAirportNdx, nextBriefTime);
			if ((nextDuties != null)
					&& (nextDuties.length > 0)) {
				for (Duty nd: nextDuties) {
					if (nd.isValid(this.hbNdx)
							&& (((dept == 1) && nd.isHbArr(this.hbNdx))
									|| ((dept == 2) && hbDep && nd.isNonHbArr(this.hbNdx))
									|| ((dept == 2) && (!hbDep) && nd.isHbArr(this.hbNdx))
									|| (dept > 2))) {
						if (this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, ld, nd)) {
							if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, nd, true)) {
								pairRuleContext.getAggregatorProxy().appendFw(p, nd);

								if (hbDep) {
				    				if (nd.isHbArr(this.hbNdx)) {
				    					if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
					    					pairingFound = true;
					    					this.setHasPairingFlag(p);
					    				}
					    			} else
					    				if (nd.isNonHbArr(this.hbNdx)
					    						&& (dept > 1)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairFW(p, fd, nd, fl, nd.getLastLeg(), true, false, dept - 1))
					    						pairingFound = true;
					    				}
								} else {
				    				if (nd.isHbArr(this.hbNdx)) {
				    					if ((dept > 1)
				    							&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    						if (this.examinePairBW(p, fd, nd, fl, nd.getLastLeg(), false, true, dept - 1))
				    							pairingFound = true;
					    				}
					    			} else
					    				if (nd.isNonHbArr(this.hbNdx)
					    						&& (dept > 1)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairFW(p, fd, nd, fl, nd.getLastLeg(), false, false, dept - 1))
					    						pairingFound = true;
					    				}									
								}

								pairRuleContext.getAggregatorProxy().removeLast(p);

								if (pairingFound) {
									if ((ld.getMinNextBriefTime(this.hbNdx) == null)
											|| ld.getMinNextBriefTime(this.hbNdx).isAfter(nd.getBriefTime(this.hbNdx)))
										ld.setMinNextBriefTime(this.hbNdx, nd.getBriefTime(this.hbNdx));
									if ((nd.getMinPrevDebriefTime(this.hbNdx) == null)
											|| nd.getMinPrevDebriefTime(this.hbNdx).isBefore(ld.getDebriefTime(this.hbNdx)))
									nd.setMinPrevDebriefTime(this.hbNdx, ld.getDebriefTime(this.hbNdx));
									break;
								}
							}
						}
					}
				}
			}
			if (pairingFound)
				break;
			hourCounter++;
			nextBriefTime = ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter);
			if (hourCounter > this.maxIdleTimeInAPairInHours)
				break;
			if (ChronoUnit.HOURS.between(fd.getBriefTime(this.hbNdx), ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter)) > this.maxPairingLengthInHours)
				break;
		}

		return pairingFound;
	}

	private boolean examinePairBW(Pair p, Duty fd, Duty ld, Leg fl, Leg ll, boolean hbDep, boolean hbArr, int dept) {

		if (hbDep)
			logger.error("Invalid search direction!");
		if (dept < 1)
			logger.error("Lack of dept!");

		boolean pairingFound = false;

		Duty[] prevDuties = null;

		int firstAirportNdx = fl.getDepAirport().getNdx();

		int hourCounter = 0;
		LocalDateTime prevNextBriefTime = fd.getBriefTime(this.hbNdx);

		while (true) {
			prevDuties = this.dutyIndexByArrAirportNdxNextBrieftime.getArray(firstAirportNdx, prevNextBriefTime);
			if ((prevDuties != null)
					&& (prevDuties.length > 0)) {
				for (Duty pd: prevDuties) {
					if (pd.isValid(this.hbNdx)
							&& (((dept == 1) && pd.isHbDep(this.hbNdx))
									|| ((dept == 2) && hbArr && pd.isNonHbDep(this.hbNdx))
									|| ((dept == 2) && (!hbArr) && pd.isHbDep(this.hbNdx))
									|| (dept > 2))) {
						if (this.dutyRuleContext.getConnectionCheckerProxy().areConnectable(this.hbNdx, pd, fd)) {
							if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, pd, false)) {
								pairRuleContext.getAggregatorProxy().appendBw(p, pd);

								if (hbArr) {
				    				if (pd.isHbDep(this.hbNdx)) {
				    					if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
					    					pairingFound = true;
					    					this.setHasPairingFlag(p);
					    				}
					    			} else
					    				if (pd.isNonHbDep(this.hbNdx)
					    						&& (dept > 1)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairBW(p, pd, ld, pd.getFirstLeg(), ll, false, true, dept - 1))
					    						pairingFound = true;
					    				}
								} else {
				    				if (pd.isHbDep(this.hbNdx)) {
				    					if ((dept > 1)
				    							&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
				    						if (this.examinePairFW(p, pd, ld, pd.getFirstLeg(), ll, true, false, dept - 1))
				    							pairingFound = true;
					    				}
					    			} else
					    				if (pd.isNonHbDep(this.hbNdx)
					    						&& (dept > 1)
					    						&& this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
					    					if (this.examinePairBW(p, pd, ld, pd.getFirstLeg(), ll, false, false, dept - 1))
					    						pairingFound = true;
					    				}
								}

								pairRuleContext.getAggregatorProxy().removeFirst(p);

								if (pairingFound) {
									if ((pd.getMinNextBriefTime(this.hbNdx) == null)
											|| pd.getMinNextBriefTime(this.hbNdx).isAfter(fd.getBriefTime(this.hbNdx)))
										pd.setMinNextBriefTime(this.hbNdx, fd.getBriefTime(this.hbNdx));
									if ((fd.getMinPrevDebriefTime(this.hbNdx) == null)
											|| fd.getMinPrevDebriefTime(this.hbNdx).isBefore(pd.getDebriefTime(this.hbNdx)))
										fd.setMinPrevDebriefTime(this.hbNdx, pd.getDebriefTime(this.hbNdx));
									break;
								}
							}
						}
					}
				}
			}
			if (pairingFound)
				break;
			hourCounter++;
			prevNextBriefTime = fd.getBriefTime(this.hbNdx).minusHours(hourCounter);
			if (hourCounter > this.maxIdleTimeInAPairInHours)
				break;
			if (ChronoUnit.HOURS.between(fd.getBriefTime(this.hbNdx), ld.getNextBriefTime(this.hbNdx).plusHours(hourCounter)) > this.maxPairingLengthInHours)
				break;
		}

		return pairingFound;
	}

}
