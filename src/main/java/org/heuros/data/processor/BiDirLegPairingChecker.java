package org.heuros.data.processor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.data.repo.DataRepository;
import org.heuros.data.model.Duty;
import org.heuros.data.model.Leg;
import org.heuros.data.model.Pair;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.PairRuleContext;

public class BiDirLegPairingChecker implements Callable<Boolean> {

	private static Logger logger = Logger.getLogger(BiDirLegPairingChecker.class);

	private int hbNdx = -1;
	private LocalDateTime coverPeriodEndExc = null;

	public BiDirLegPairingChecker(int hbNdx, LocalDateTime coverPeriodEndExc) {
		this.hbNdx = hbNdx;
		this.coverPeriodEndExc = coverPeriodEndExc;
	}

	private int maxIdleTimeInAPairInHours = 0;
	private int maxPairingLengthInHours = 0;
	private List<Leg> legs = null;
	private List<Duty> duties = null;
	private DutyRuleContext dutyRuleContext = null;
	private PairRuleContext pairRuleContext = null;
	private OneDimIndexInt<Duty> dutyIndexByLegNdx = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime= null;

	public BiDirLegPairingChecker setMaxIdleTimeInAPairInHours(int maxIdleTimeInAPairInHours) {
		this.maxIdleTimeInAPairInHours = maxIdleTimeInAPairInHours;
		return this;
	}

	public BiDirLegPairingChecker setMaxPairingLengthInHours(int maxPairingLengthInHours) {
		this.maxPairingLengthInHours = maxPairingLengthInHours;
		return this;
	}

	public BiDirLegPairingChecker setLegRepository(DataRepository<Leg> legRepository) {
		this.legs = legRepository.getModels();
		return this;
	}

	public BiDirLegPairingChecker setDutyRepository(DataRepository<Duty> dutyRepository) {
		this.duties = dutyRepository.getModels();
		return this;
	}

	public BiDirLegPairingChecker setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}

	public BiDirLegPairingChecker setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	public BiDirLegPairingChecker setDutyIndexByLegNdx(OneDimIndexInt<Duty> dutyIndexByLegNdx) {
		this.dutyIndexByLegNdx = dutyIndexByLegNdx;
		return this;
	}

	public BiDirLegPairingChecker setDutyIndexByDepAirportNdxBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime) {
		this.dutyIndexByDepAirportNdxBrieftime = dutyIndexByDepAirportNdxBrieftime;
		return this;
	}

	public BiDirLegPairingChecker setDutyIndexByArrAirportNdxNextBrieftime(TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime) {
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
			for (int j = 0; j < d.getNumOfLegs(); j++) {
				Leg l = d.getLegs().get(j);
				l.setHasPair(this.hbNdx, true);
				if (p.getNumOfDuties() == 1) {
					l.setHasHbDepArrDutyPair(this.hbNdx, true);
				} else
					if (i == 0) {
						l.setHasHbDepDutyPair(this.hbNdx, true);
					} else
						if (i == p.getNumOfDuties() - 1) {
							l.setHasHbArrDutyPair(this.hbNdx, true);
						} else
							l.setHasNonHbDutyPair(this.hbNdx, true);
			}
		}
	}

	@Override
	public Boolean call() {

    	logger.info("Leg pairing generation check is started!");

		boolean[] dutiesChecked = new boolean[this.duties.size()];

		Pair p = Pair.newInstance(this.hbNdx);

		for (int li = 0; li < this.legs.size(); li++) {
    		Leg l = this.legs.get(li);

    		if (l.isCover()
            		&& l.getSobt().isBefore(coverPeriodEndExc)) {
//            	int pairingFound = 0;
//            	if (l.hasHbDepArrDutyPair(this.hbNdx))
//            		pairingFound += 1;
//            	if (l.hasHbDepDutyPair(this.hbNdx))
//            		pairingFound += 2;
//            	if (l.hasNonHbDutyPair(this.hbNdx))
//            		pairingFound += 4;
//            	if (l.hasHbArrDutyPair(this.hbNdx))
//            		pairingFound += 8;

//if (l.getNdx() == 40745)
//System.out.println(pairingFound);

    			Duty[] ds = this.dutyIndexByLegNdx.getArray(l.getNdx());

            	if ((ds != null)
            			&& (ds.length > 0)) {

            		for (Duty d: ds) {

//if (d.getNdx() == 445)
//System.out.println(pairingFound);

            			if (d.isValid(this.hbNdx)
            					&& d.hasPairing(this.hbNdx)
            					&& (!dutiesChecked[d.getNdx()])) {

//if ((l.getNdx() == 1017) && (d.getNdx() == 7045))
//System.out.println(pairingFound);

	    					/*
	    					 * HB dep and arr.
	    					 */
				    		if ((!l.hasHbDepArrDutyPair(this.hbNdx))
				    				&& d.isHbDep(this.hbNdx)
				    				&& d.isHbArr(this.hbNdx)) {
//				    			if ((pairingFound & 1) > 0)
//				    				logger.error("Pairing is already found before!");
					    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
						    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
						    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
						    			if (this.pairRuleContext.getFinalCheckerProxy().acceptable(this.hbNdx, p)) {
						    				if (p.isComplete(this.hbNdx)) {
						    					/*
						    					 * Set related leg flags!
						    					 */
						    					this.setHasPairingFlag(p);
//					    						pairingFound |= 1;
						    				} else
						    					logger.error("Pairing " + d + " must be complete!");
						    			}
						    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
						    		}
					    		}
					    		dutiesChecked[d.getNdx()] = true;
				    		} else
		    					/*
		    					 * HB dep only.
		    					 */
					    		if ((!l.hasHbDepDutyPair(this.hbNdx))
					    				&& d.isHbDep(this.hbNdx)
					    				&& d.isNonHbArr(this.hbNdx)) {
//					    			if ((pairingFound & (1 << 1)) > 0)
//					    				logger.error("Pairing is already found before!");
						    		if (this.pairRuleContext.getStarterCheckerProxy().canBeStarter(this.hbNdx, d)) {
							    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, true)) {
							    			this.pairRuleContext.getAggregatorProxy().appendFw(p, d);
							    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
							    				if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 1)) {
							    					/*
							    					 * Set related leg flags!
							    					 */
//							    					pairingFound |= (1 << 1);
							    				} else
							    					if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 2)) {
								    					/*
								    					 * Set related leg flags!
								    					 */
//							    						pairingFound |= (1 << 1);
								    				} else
								    					if (this.examinePairFW(p, d, d, d.getFirstLeg(), d.getLastLeg(), true, false, 3)) {
									    					/*
									    					 * Set related leg flags!
									    					 */
//								    						pairingFound |= (1 << 1);
								    					}
							    			}
							    			this.pairRuleContext.getAggregatorProxy().removeLast(p);
							    		}
						    		}
						    		dutiesChecked[d.getNdx()] = true;
						    	} else
			    					/*
			    					 * Non HB dep and arr.
			    					 */
						    		if ((!l.hasNonHbDutyPair(this.hbNdx))
						    				&& d.isNonHbDep(this.hbNdx)
						    				&& d.isNonHbArr(this.hbNdx)) {
//						    			if ((pairingFound & (1 << 2)) > 0)
//						    				logger.error("Pairing is already found before!");
							    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
							    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
							    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
							    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, false, 2)) {
							    					/*
							    					 * Set related leg flags!
							    					 */
//							    					pairingFound |= (1 << 2);
							    				} else
								    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, false, 3)) {
								    					/*
								    					 * Set related leg flags!
								    					 */
//								    					pairingFound |= (1 << 2);
								    				}
							    			}
							    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
							    		}
							    		dutiesChecked[d.getNdx()] = true;
							    	} else
				    					/*
				    					 * HB arr only.
				    					 */
							    		if ((!l.hasHbArrDutyPair(this.hbNdx))
							    				&& d.isNonHbDep(this.hbNdx)
							    				&& d.isHbArr(this.hbNdx)) {
//							    			if ((pairingFound & (1 << 3)) > 0)
//							    				logger.error("Pairing is already found before!");
								    		if (this.pairRuleContext.getAppendabilityCheckerProxy().isAppendable(this.hbNdx, p, d, false)) {
								    			this.pairRuleContext.getAggregatorProxy().appendBw(p, d);
								    			if (this.pairRuleContext.getExtensibilityCheckerProxy().isExtensible(this.hbNdx, p)) {
								    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 1)) {
								    					/*
								    					 * Set related leg flags!
								    					 */
//								    					pairingFound |= (1 << 3);
								    				} else
									    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 2)) {
									    					/*
									    					 * Set related leg flags!
									    					 */
//									    					pairingFound |= (1 << 3);
									    				} else
										    				if (this.examinePairBW(p, d, d, d.getFirstLeg(), d.getLastLeg(), false, true, 3)) {
										    					/*
										    					 * Set related leg flags!
										    					 */
//										    					pairingFound |= (1 << 3);
										    				}
								    			}
								    			this.pairRuleContext.getAggregatorProxy().removeFirst(p);
								    		}
								    		dutiesChecked[d.getNdx()] = true;
							    		}
            			}
            		}
    			}
//        		logger.info(l + " - " + l.hasHbDepArrDutyPair(this.hbNdx) + " " + l.hasHbDepDutyPair(this.hbNdx) + " " + l.hasNonHbDutyPair(this.hbNdx) + " " + l.hasHbArrDutyPair(this.hbNdx));
	    	}
		}

    	logger.info("Leg pairing generation check finished!");

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

								if (pairingFound)
									break;
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

								if (pairingFound)
									break;
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
