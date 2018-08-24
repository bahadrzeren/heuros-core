package org.heuros.data.processor;

public class PairGenerator {

//	private int fNdxFrom = 0;
//	private int fNdxTo = 0;
//
//	private int dNdxFrom = 0;
//	private int dNdxTo = 0;
//
//	public Pair[] ps = null;
////	public Pair[] psEx = null;
//
//	private int pcf = 0;
//	private int nopTarget = 0;
//	private int nopSince = 0;
//
//	private Flight processF = null;
////	private int processHb = 0;
//
////	private boolean overCovered = false;
//
//	private double searchDept = 0.0;
////	private double prevSearchDept = 0.0;
//
//    private int reducedCostElm = 0;
//    private int passed = 0;
//
//    private double rcCurrent = 0.0;
//
//    private double rcTreshold1 = 0.0;
//    private double rcTreshold2 = 0.0;
//    private double rcTreshold3 = 0.0;
//
//    private long algStep = 0;
//    public void setAlgStep(long algStep) {
//    	this.algStep = algStep;
//    }
////    private boolean genPairs = false;
////    public void setGenPairs(boolean genPairs) {
////    	this.genPairs = genPairs;
////    }
//
//    public PairGenerator(int pairGenNdx,
//					int fNdxFrom,
//					int fNdxTo,
//					int dNdxFrom,
//					int dNdxTo) {
//		super("Job:PairGen[" + pairGenNdx + "]-" + DatetimeUtil.toDayString());
//
//		this.repo = RuleFacade.getRepo();
//		this.ruleManager = RuleFacade.getRm();
//
//		this.logPrefix = "Job:PairGen[" + pairGenNdx + "] - ";
//
//		this.fNdxFrom = fNdxFrom;
//		if (fNdxTo > SourceStore.getFlights().length)
//			this.fNdxTo = SourceStore.getFlights().length;
//		else
//			this.fNdxTo = fNdxTo;
//		this.dNdxFrom = dNdxFrom;
//		if (dNdxTo > DutyStore.getDuties().length)
//			this.dNdxTo = DutyStore.getDuties().length;
//		else
//			this.dNdxTo = dNdxTo;
//
//		BaatarSession.getLogger().logInfo(logPrefix, "PairGen is created. fFrom " + fNdxFrom + " fTo " + fNdxTo + " dFrom " + dNdxFrom + " dTo " + dNdxTo);
//	}
//
//	@Override
//	public boolean initializeThreadManager() {
//		return true;
//	}
//
//	@Override
//	public boolean finalizeThreadManager() {
//		return true;
//	}
//
//	DecimalFormat df = new DecimalFormat("#.##");
//
//	@Override
//	protected void goAhead() throws Exception {
//		BaatarSession.getLogger().logInfo(logPrefix, "Duties " + dNdxFrom + " -> " + dNdxTo + " are being processed.");
//
//		reducedCostElm = 0;
//	    passed = 0;		
//
//		Duty[] dsf = null;
//		Duty d = null;
//
//		Pair p = Pair.getInstance(BaatarItr.getMainStep());
//		Airport depAp = null;
//		Airport arrAp = null;
//
//		int pcSince = 0;
//
////		prevSearchDept = searchDept;
//		if (BaatarItr.getCounter() >= BaatarSetting.getNumOfInclusionSteps()) {
//			searchDept = 1.0;
//		} else {
//			searchDept = (1.0 * BaatarItr.getCounter()) / BaatarSetting.getNumOfInclusionSteps();
//		}
//
//		rcTreshold1 *= (1.0 - BaatarItr.getConvRate()) * BaatarItr.getRcRate() * BaatarItr.getRcRate();
//	    rcTreshold2 *= (1.0 - BaatarItr.getConvRate()) * BaatarItr.getRcRate() * BaatarItr.getRcRate();
//	    rcTreshold3 *= (1.0 - BaatarItr.getConvRate()) * BaatarItr.getRcRate() * BaatarItr.getRcRate();
//
//		int rDay = -1;
//	    int fDay = 0;
//
//	    int i = fNdxFrom;
//	    int iDay = i;
//	    int pcfSrc = 0;
//
//	    while ((!processCancelled()) && (i < fNdxTo)) {
//
//			processF = SourceStore.getFlights()[i];
//
////			overCovered = processF.asiriKapAdm == job.pStep;
//
//			fDay = DatetimeUtil.getDateInDays(processF.getScheduledOffblock(), 0, repo.getDutyStartHour());
//			if (fDay != rDay) {
//
//				if ((i > iDay) && (ps.length == 0)
//						&& ((rcTreshold1 > 0.0) || (rcTreshold2 > 0.0) || (rcTreshold3 > 0.0))) {
//					i = iDay;
//					rcTreshold1 = 0.0;
//				    rcTreshold2 = 0.0;
//				    rcTreshold3 = 0.0;
//
//				    pcfSrc = -2;										//										??????????????????????????????????????????????????????????
//
//				    BaatarSession.getLogger().logInfo(logPrefix, "Threshold update! " + rcTreshold1 + ", " + rcTreshold2 + ", " + rcTreshold3);
//				} else {
//					rDay = fDay;
//					iDay = i;
//					rcTreshold1 *= BaatarItr.getRcRate();
//				    rcTreshold2 *= BaatarItr.getRcRate();
//				    rcTreshold3 *= BaatarItr.getRcRate();
//	
//				    pcfSrc = 0;
//
//				    BaatarSession.getLogger().logInfo(logPrefix, pcSince + " pairs " + rcTreshold1 + ", " + rcTreshold2 + ", " + rcTreshold3);
//				}
//
//				pcSince = 0;
//			}
//
//			pcf = pcfSrc;
//
//			processF = SourceStore.getFlights()[i];
//
//			dsf = DutyStore.getDs_FlightOrg().getArray(processF.ndx);
//
//			int maxDept = (int) Math.ceil(dsf.length * searchDept);
////			int prevDept = (int) Math.ceil(dsf.length * prevSearchDept);
//
//			for (int j = 0; j < dsf.length; j++) {
//				d = dsf[j];
//
//				if (j > maxDept)
//					break;
//
////if ((maxDept >= 0) && (d.agIcSyc < 0) && ((d.elmadm < 0) || overCovered))
//if ((maxDept >= 0) && (d.agIcSyc < 0) && (d.elmadm < 0))
//	if (d.mOrt < d.dptgf)// < job.dRcMean * 0.382)																		//			??????????????????????????????????????	Bu kaliteyi etkilemez mi ?
//		d.bAgIcSyc(BaatarItr.getCounter());
//
//				if (	//genPairs && 
//						processF.compCvr
//						&& (((processF.dpdMin > BaatarSetting.getSolverSens()) && (processF.dpdMin < (1.0 - BaatarSetting.getSolverSens())))			//			??????????????????????????????????????
//								|| (processF.dpdTop > (1.0 + BaatarSetting.getSolverSens()))											//			??????????????????????????????????????
//								|| (//(job.numOfFracs < job.maxNumOfFracsForSpPairGen) && 
//										(processF.dpgf > BaatarItr.getRowSpMean())))
//						) {
//
//depAp = d._ff().getDepAirport();
//arrAp = d._lf().getArrAirport();
//
//if (d.solSinir) {
//	if (arrAp._hb())
//		pcSince = searchForPair(arrAp._hbNdx, pcSince, d, depAp, arrAp, p);
//	else
//		pcSince = searchForPair(-1, pcSince, d, depAp, arrAp, p);
//} else
//	if (d.sagSinir) {
//		if (depAp._hb())
//			pcSince = searchForPair(depAp._hbNdx, pcSince, d, depAp, arrAp, p);
//		else
//			pcSince = searchForPair(-1, pcSince, d, depAp, arrAp, p);
//	} else {
//		if (depAp._hb()) {
//			pcSince = searchForPair(depAp._hbNdx, pcSince, d, depAp, arrAp, p);
//		}
//		if (arrAp._hb() && (depAp._hbNdx != arrAp._hbNdx)) {
//			pcSince = searchForPair(arrAp._hbNdx, pcSince, d, depAp, arrAp, p);
//		}
//	}
//				}
//			}
//			i++;
//		}
//
////	    if (ps.length == 0)
////	    	pRcRateEnd *= 0.9;
//
//	    BaatarSession.getLogger().logInfo(logPrefix, ps.length + " number of pairs generated.");
//	    BaatarSession.getLogger().logInfo(logPrefix, "RedCostElm : " + reducedCostElm);
//	    BaatarSession.getLogger().logInfo(logPrefix, "Passed : " + passed);
//	}
//
//	private int searchForPair(int processHb, int pcSince, Duty d, Airport depAp, Airport arrAp, Pair p) throws CloneNotSupportedException {
//		if (d.sSedadm(algStep, processHb)
////				&& ((d.elmadm < 0) || overCovered)
//				&& (d.elmadm < 0)
//				&& (pcf < BaatarSetting.getMaxNofPairsGenPerFlight())
//				&& ((d.cidi == BaatarItr.getCounter() - 1)
//						|| ((d.m(processHb) - d.dptgf) < BaatarItr.getSubColRcMean()))
//					) {
//
//				rcCurrent = d.dptgf - d.m(processHb);
//
//				nopTarget = 1;
//				nopSince = 0;
//
//				Flight ff = d._ff();
//				Flight lf = d._lf();
//
//				p.ge(d, true);
//
//				if (d.solSinir || d.sagSinir || (depAp._hb(processHb) && arrAp._hb(processHb))) {
//					if ((depAp._hb(processHb) && arrAp._hb(processHb) && d._legalHb)
//									|| (d.solSinir && (d._legalHb || d._legalNonHb))
//									|| (d.sagSinir && d._legalNonHb)) {
//						if ((depAp._hb(processHb) && arrAp._hb(processHb) && d._legalHb)
//								|| (d.solSinir && arrAp._hb(processHb) && (depAp._hbNdx != arrAp._hbNdx) && d._legalNonHb)
//								|| (d.sagSinir && depAp._hb(processHb) && (depAp._hbNdx != arrAp._hbNdx) && d._legalHb)) {
//							nopSince = 0;
//							if (rcCurrent > rcTreshold1 * BaatarItr.getRcRate()) {
//	                			if (isPairQualitySufficient(p)) {
//	                				evalPair(p);
//	                			}
//								pcSince += nopSince;
//							}
//						}
//						if (processHb < 0) {
//							if (d.solSinir) {
//								nopSince = 0;
//								examinePairFW(p, d, d, ff, lf, -1);
//								pcSince += nopSince;
//							} else
//								if (d.sagSinir) {
//									nopSince = 0;
//									examinePairBW(p, d, d, ff, lf, -1);
//									pcSince += nopSince;
//								}
//						}
//					}
//				} else
//					if (depAp._hb(processHb) && d._legalHb) {
//						nopSince = 0;
//                    	examinePairFW(p, d, d, ff, lf, processHb);
//                    	pcSince += nopSince;
//					} else
//						if (arrAp._hb(processHb) && d._legalNonHb) {
//							nopSince = 0;
//	                    	examinePairBW(p, d, d, ff, lf, processHb);
//	                    	pcSince += nopSince;
//						}
//				p.sgc();
//            	d.bSedadm(algStep, processHb);
//			}
//		return pcSince;
//	}
//
//	private void examinePairFW(Pair p, Duty fd, Duty ld, Flight ff, Flight lf, int processHb) throws CloneNotSupportedException {
//
//    	double rcTreshold = rcTreshold2;
////		if (trgtl == 3)
////			rcTreshold = rcTreshold3;
//
//        int arrAPndx = lf.getArrAirport()._ndx;
//
//        int minDepDateInHours = DatetimeUtil.getDateInHours(ld._nextBriefTimeMin);
//
//        int idleTime = 0;
//
//        int hc = 0;
//
//        boolean cc = true;
//
//        Duty[] lDl = null;
//        Duty d = null;
//
//        boolean pairEnds = false;
//
//        int maxDept = (int) Math.ceil(BaatarItr.getCounter() * 2 * lf.varGorBagSay);
//        int numOfEval = 0;
//
//        Flight ffOfDutyFound = null;
//        Flight lfOfDutyFound = null;
//
//        while (cc) {
//
//    		lDl = DutyStore.getDs_DepAP_BriefTime().getArray(arrAPndx, minDepDateInHours);
//
//            if (lDl != null) {
//                for (int i = 0; i < lDl.length; i++) {
//
//                    d = lDl[i];
//
//                    ffOfDutyFound = d._ff();
//                    lfOfDutyFound = d._lf();
//
//                    cc = goDeeper(ff, ffOfDutyFound);
//
//                    if (!cc)
//                        break;
//
//ld.bEuVarSsKal(hc);
//
//if (d.sSedadm(algStep, processHb)
//		 && d._legalNonHb
//		 &&
////		 (overCovered ||
//				 ((d.elmadm < 0)
//				&& ((ld.sonkiSbtUcsBagNdx < 0)
//						|| (ld.sonkiSbtUcsBagNdx == ffOfDutyFound.ndx))
//				&& ((d.onckiSbtUcsBagNdx < 0)
//						|| (d.onckiSbtUcsBagNdx == lf.ndx)))
////						)
//				) {
//
//	if (((rcCurrent + d.dptgf - d.m(processHb)) > rcTreshold * BaatarItr.getRcRate())
//			|| (d.cidi == BaatarItr.getCounter() - 1)) {
//
//					pairEnds = (ff.getDepAirport()._hb(processHb) && lfOfDutyFound.getArrAirport()._hb(processHb) && fd._legalHb)
//								|| (d.sagSinir && fd._legalHb)
//								|| (fd.solSinir && lfOfDutyFound.getArrAirport()._hb() && (((ff.getDepAirport()._hbNdx == lfOfDutyFound.getArrAirport()._hbNdx) && fd._legalHb)
//																							|| ((ff.getDepAirport()._hbNdx != lfOfDutyFound.getArrAirport()._hbNdx) && fd._legalNonHb)));
//
////                	if (d._legalNonHb
////                			&& (((trgtl > crrntl)
////	                    		&& (!pairEnds))
////	                    		|| ((trgtl == crrntl)
////	        					&& (pairEnds)))) {
//                	if (pairEnds) {
//
//                		idleTime = DatetimeUtil.getDateDiffInMins(ld._nextBriefTimeMin, d._briefTimeNonHb);
//
//                		if (idleTime >= 0)
//                			if (ruleManager._isDutyAddable(p, d)
//                					&& ruleManager._areDutiesConnectable(p._ld(), d)) {
//
//                                numOfEval++;
//                                if (numOfEval > maxDept)
//                                	break;
//
//                				p.ge(d, true);
//
//	                    		if (ruleManager._isPairValid(p)) {
//	                    			if (isPairQualitySufficient(p)) {
//	                    				if (pairEnds)
//	                    					evalPair(p);
////	                    				else {
////                    						rcCurrent += (d.dpugf - d.m(processHb));
////                                        	examinePairFW(p, fd, d, processHb, trgtl, crrntl + 1);
////                                        	rcCurrent -= (d.dpugf - d.m(processHb));
////                    					}
//	                    			}
//	                    		}
//
//	                            p.sgc();
//
//                			}
//                    }
//	}
//}
//                }
//            }
//
//            if (!cc)
//                break;
//
//            if (numOfEval > maxDept)
//            	break;
//
//            minDepDateInHours++;
//            hc++;
//
//            cc = !(((nopSince > 0) && hc > repo.getMaxIdleTimeInAPair())
//            		|| (hc > (repo.getMaxLay() / 60)));
//        }
//    }
//
//    private void examinePairBW(Pair p, Duty fd, Duty ld, Flight ff, Flight lf, int processHb) throws CloneNotSupportedException {
//
//    	double rcTreshold = rcTreshold2;
////		if (trgtl == 3)
////			rcTreshold = rcTreshold3;
//
//        int depAPndx = ff.getDepAirport()._ndx;
//
//        int maxArrDateInHours = DatetimeUtil.getDateInHours(fd._briefTimeNonHb);
//
//        int idleTime = 0;
//
//        int hc = 0;
//
//        boolean cc = true;
//
//        Duty[] fDl = null;
//        Duty d = null;
//
//        boolean pairEnds = false;
//
//        int maxDept = (int) Math.ceil(BaatarItr.getCounter() * 2 * ff.kalGorBagSay);
//        int numOfEval = 0;
//
//        Flight ffOfDutyFound = null;
//        Flight lfOfDutyFound = null;
//
//        while (cc) {
//
//    		fDl = DutyStore.getDs_ArrAP_RestEndTime().getArray(depAPndx, maxArrDateInHours);
//
//            if (fDl != null) {
//                for (int i = 0; i < fDl.length; i++) {
//
//                    d = fDl[i];
//
//                    ffOfDutyFound = d._ff();
//                    lfOfDutyFound = d._lf();
//
//                    cc = goDeeper(ffOfDutyFound, ld._ff());
//
//                    if (!cc)
//                        break;
//
//d.bEuVarSsKal(hc);
//
//                    if ((d.ndx >= dNdxFrom)
//                    		&& d.sSedadm(algStep, processHb)
//	    					 &&
////	    					 (overCovered || 
//	    							 ((d.elmadm < 0)
//		                    		&& ((d.sonkiSbtUcsBagNdx < 0)
//		                    				|| (d.sonkiSbtUcsBagNdx == ff.ndx))
//		                    		&& ((fd.onckiSbtUcsBagNdx < 0)
//		                    				|| (fd.onckiSbtUcsBagNdx == lfOfDutyFound.ndx)))
////		                    				)
//                    				) {
//
//if (((rcCurrent + d.dptgf - d.m(processHb)) > rcTreshold * BaatarItr.getRcRate())
//	|| (d.cidi == BaatarItr.getCounter() - 1)) {
//
////                    	pairEnds = ffOfDutyFound.getDepAirport()._hb(processHb) && lf.getArrAirport()._hb(processHb);
//
////						pairEnds = (ff.getDepAirport()._hb(processHb) && lfOfDutyFound.getArrAirport()._hb(processHb) && fd._legalHb)
////									|| (d.sagSinir && fd._legalHb)
////									|| (fd.solSinir && lfOfDutyFound.getArrAirport()._hb() && (((ff.getDepAirport()._hbNdx == lfOfDutyFound.getArrAirport()._hbNdx) && fd._legalHb)
////																								|| ((ff.getDepAirport()._hbNdx != lfOfDutyFound.getArrAirport()._hbNdx) && fd._legalNonHb)));
//
//    					pairEnds = (ffOfDutyFound.getDepAirport()._hb(processHb) && lf.getArrAirport()._hb(processHb) && d._legalHb)
//    								|| (ld.sagSinir && ffOfDutyFound.getDepAirport()._hb() && d._legalHb)
//    								|| (d.solSinir && (((ffOfDutyFound.getDepAirport()._hbNdx == lf.getArrAirport()._hbNdx) && d._legalHb)
//    													|| ((ffOfDutyFound.getDepAirport()._hbNdx != lf.getArrAirport()._hbNdx) && d._legalNonHb)));
//
//
//
////	                    if (((trgtl > crrntl)
////	                    		&& (!pairEnds)
////	                    		&& d._legalNonHb)
////	                		|| ((trgtl == crrntl)
////	        					&& (pairEnds)
////	        					&& d._legalHb
////	        					&& d.g)) {
//	                    if (pairEnds) {
//
//	                		idleTime = DatetimeUtil.getDateDiffInMins(d._nextBriefTimeMin, fd._briefTimeNonHb);
//
//	                		if (idleTime >= 0)
//
//	                			if (ruleManager._isDutyAddable(p, d)
//	                					&& ruleManager._areDutiesConnectable(d, p._fd())) {
//
//	                                numOfEval++;
//	                                if (numOfEval > maxDept)
//	                                	break;
//
//		                    		p.ge(d, false);
//
//		                    		if (ruleManager._isPairValid(p)) {
//		                    			if (isPairQualitySufficient(p)) {
//		                    				if (pairEnds)
//		                    					evalPair(p);
////		                    				else {
////	                    						rcCurrent += (d.dpugf - d.m(processHb));
////	                                        	examinePairBW(p, d, ld, processHb, trgtl, crrntl + 1);
////	                                        	rcCurrent -= (d.dpugf - d.m(processHb));
////	                    					}
//		                    			}
//		                    		}
//
//		                    		p.igc();
//		                        }
//	                    }
//}
//                    }
//                }
//            }
//
//            if (!cc)
//                break;
//
//            if (numOfEval > maxDept)
//            	break;
//
//            maxArrDateInHours--;
//            hc++;
//
//            cc = !(((nopSince > 0) && hc > repo.getMaxIdleTimeInAPair())
//            		|| (hc > (repo.getMaxLay() / 60)));
//        }
//    }
///*
//    private void examinePairMFW(Pair p, int trgtl, int crrntl) throws CloneNotSupportedException {
//
//    	double rcTreshold = rcTreshold2;
//		if (trgtl == 3)
//			rcTreshold = rcTreshold3;
//
//        Duty ld = p.getDuties().get(p.getDuties().size() - 1);
//        Flight lf = ld._lf();
//
//        int arrAPndx = lf.getArrAirport()._ndx;
//
//        int minDepDateInHours = DatetimeUtil.getDateInHours(ld._nextBriefTimeNonHb);
//
//        int idleTime = 0;
//
//        int hc = 0;
//
//        boolean cc = true;
//
//        Duty[] lDl = null;
//        Duty d = null;
//
//        int maxDept = (int) Math.ceil(job.counter * 2 * lf._numOfDutyConnAtArr);
//        int numOfEval = 0;
//
//        boolean nonHb = false;
//        if (processHb < 0)
//        	nonHb = true;
//
//        while (cc) {
//
//        	lDl = job.ds.ds_DepAP_BriefTime.getArray(arrAPndx, minDepDateInHours);
//
//            if (lDl != null) {
//                for (int i = 0; i < lDl.length; i++) {
//
//                    d = lDl[i];
//
//                    if (d._lf().getArrAirport()._hb()) {
//                		if (nonHb)
//                			processHb = d._lf().getArrAirport()._hbNdx;
//
//                    	cc = (trgtl > crrntl) && goDeeper(p._fd(), d);
//
//	                    if (!cc)
//	                        break;
//
//ld._setPgArrHcDept(hc);
//
//if (d._lpaas(algStep, processHb)
//		&& (!d._eliminated)
//		&& ((ld._nextFixedDutyConnFNdx < 0)
//				|| (ld._nextFixedDutyConnFNdx == d._ff()._ndx))
//		&& ((d._prevFixedDutyConnFNdx < 0)
//				|| (d._prevFixedDutyConnFNdx == ld._lf()._ndx))
//		) {
//
//	if ((rcCurrent + d._sp - d._cost(processHb)) > rcTreshold * pRcRate) {
//
//						idleTime = DatetimeUtil.getDateDiffInMins(ld._nextBriefTimeNonHb, d._briefTime);
//
//	                    if (idleTime >= 0) {
//	            			if (ruleManager._isDutyAddable(p, d)
//	               					&& ruleManager._areDutiesConnectable(p._ld(), d)) {
//
//	                            numOfEval++;
//	                            if (numOfEval > maxDept)
//	                            	break;
//
//	            				p.attachDuty(functionManager, d, true);
//		                        if (ruleManager._isPairValid(p)) {
//		                        	rcCurrent += (d._sp - d._cost(processHb));
//		                            examinePairBW(p, trgtl, crrntl + 1);
//		                            rcCurrent -= (d._sp - d._cost(processHb));
//		                        }
//		                        p.detachLastDuty(functionManager);
//	                    	}
//	                    }
//	}
//}
//if (nonHb)
//	processHb = -1;
//                    }
//                }
//            }
//
//            if (!cc)
//                break;
//
//            if (numOfEval > maxDept)
//            	break;
//
//            minDepDateInHours++;
//            hc++;
//
//            cc = !(((nopSince > 0) && hc > systemParam.getMaxIdleTimeInAPair())
//            		|| (hc > (systemParam.getMaxIntLay() / 60)));
//        }
//    }
//
//    private void examinePairMBW(Pair p, int trgtl, int crrntl) throws Exception {
//
//    	double rcTreshold = rcTreshold2;
//		if (trgtl == 3)
//			rcTreshold = rcTreshold3;
//
//        Duty fd = p.getDuties().get(0);
//        Flight ff = fd._ff();
//
//        int depAPndx = ff.getDepAirport()._ndx;
//
//        int maxArrDateInHours = DatetimeUtil.getDateInHours(fd._briefTime);
//
//        int idleTime = 0;
//
//        int hc = 0;
//
//        boolean cc = true;
//
//        Duty[] fDl = null;
//        Duty d = null;
//
//        int maxDept = (int) Math.ceil(job.counter * 2 * ff._numOfDutyConnAtDep);
//        int numOfEval = 0;
//
//        boolean nonHb = false;
//        if (processHb < 0)
//        	nonHb = true;
//
//        while (cc) {
//
//        	fDl = job.ds.ds_ArrAP_RestEndTime.getArray(depAPndx, maxArrDateInHours);
//
//            if (fDl != null) {
//                for (int i = 0; i < fDl.length; i++) {
//
//                    d = fDl[i];
//
//                    if (d._ff().getDepAirport()._hb()) {
//                		if (nonHb)
//                			processHb = d._ff().getDepAirport()._hbNdx;
//
//                    	cc = (trgtl > crrntl) && goDeeper(d, p._ld());
//
//	                    if (!cc)
//	                        break;
//
//d._setPgArrHcDept(hc);
//
//						if ((d._ndx >= dNdxFrom)
//								&& (d._lpaas(algStep, processHb))
//								&& (!d._eliminated)
//								&& ((d._nextFixedDutyConnFNdx < 0)
//										|| (d._nextFixedDutyConnFNdx == fd._ff()._ndx))
//								&& ((fd._prevFixedDutyConnFNdx < 0)
//										|| (fd._prevFixedDutyConnFNdx == d._lf()._ndx))
//										) {
//
//if ((rcCurrent + d._sp - d._cost(processHb)) > rcTreshold * pRcRate) {
//
//							idleTime = DatetimeUtil.getDateDiffInMins(d._nextBriefTimeNonHb, fd._briefTime);
//
//		                    if (idleTime >= 0) {
//		                    	if (ruleManager._isDutyAddable(p, d)
//	                					&& ruleManager._areDutiesConnectable(d, p._fd())) {
//
//		                            numOfEval++;
//		                            if (numOfEval > maxDept)
//		                            	break;
//
//		                    		p.attachDuty(functionManager, d, false);
//			                        if (ruleManager._isPairValid(p)) {
//			                        	rcCurrent += (d._sp - d._cost(processHb));
//			                            examinePairFW(p, trgtl, crrntl + 1);
//			                            rcCurrent -= (d._sp - d._cost(processHb));
//			                        }
//			                        p.detachFirstDuty(functionManager);
//		                    	}
//		                    }
//}
//	                	}
//	                    if (nonHb)
//	                    	processHb = -1;
//	                }
//                }
//            }
//
//            if (!cc)
//                break;
//
//            if (numOfEval > maxDept)
//            	break;
//
//            maxArrDateInHours--;
//            hc++;
//
//            cc = !(((nopSince > 0) && hc > systemParam.getMaxIdleTimeInAPair())
//            		|| (hc > (systemParam.getMaxIntLay() / 60)));
//        }
//    }
//*/
//    private boolean goDeeper(Flight ffOffd, Flight ffOfld) {
//    	if (nopTarget <= nopSince)
//    		return false;
//
//    	Date firstDate = ffOffd.getScheduledOffblock();
//        Date lastDate = ffOfld.getScheduledOffblock();
////        int totalPairingPeriod = DatetimeUtil.getDateDiffInMins(firstDate, lastDate) + 60	//	60 dakikadan k�sa u�u� zaten olmaz.
////									+ functionManager.getBriefPeriod(ffOffd, true)
////						            + functionManager.getDebriefPeriod();
////
////       	return (totalPairingPeriod < systemParam.getMaxPP());
//
//        return DatetimeUtil.getDateDiffInDays(firstDate,  lastDate) <= repo.getMaxNumOfDutiesInAPair();
//    }
//
//    private boolean isPairQualitySufficient(Pair p) {
//
////    	p.gm = functionManager.getPairCost(p);
//    	Pair.postProcess(p, BaatarItr.getMainStep());
//    	boolean pass = false;
//
//    	double diff = - PairStore.getRc(p);
//
//        if (diff > 0.0) {
//        	if (p.getDuties().size() == 1) {
//        		pass = (diff >= (rcTreshold1 * BaatarItr.getRcRate())) && (rcTreshold1 > 0);
//	        	if (diff > rcTreshold1) rcTreshold1 = diff;
//	        } else
//	            if (p.getDuties().size() == 2) {
//		        	pass = (diff >= (rcTreshold2 * BaatarItr.getRcRate())) && (rcTreshold2 > 0);
//	            	if (diff > rcTreshold2) rcTreshold2 = diff;
//	            } else
//	                if (p.getDuties().size() == 3) {
//	                	pass = (diff >= (rcTreshold3 * BaatarItr.getRcRate())) && (rcTreshold3 > 0);
//	                	if (diff > rcTreshold3) rcTreshold3 = diff;
//                    }
//        }
//        if (!pass) {
//        	reducedCostElm++;
//        	return false;
//    	} else {
//    		passed++;
//    	}
//
//        return true;
//    }
//
//    @Override
//	protected void statusChanged(ThreadStatus oldThreadStatus,
//		ThreadStatus newThreadStatus) {
//	}
//
//	@Override
//	protected void onExceptionOccured(Exception ex) {
//		BaatarSession.getLogger().logError(logPrefix, ex);
//	}
//
//	private void evalPair(Pair p) throws CloneNotSupportedException {
//		Pair pp = PairStore.isPairGeneratedBefore(p);
//		if (pp == null) {
//			pp = (Pair) p.clone();
//			ps = ArrayUtils.add(ps, pp);
//			nopSince++;
//			pcf++;
//
//BaatarSession.getLogger().logInfo(logPrefix, "A pair is generated for " + processF.toShortString());
//
//		} else {
////			Duty di = null;
////			Flight fi = null;
////			if (pp.adm < job.counter) {
////				for (int x = 0; (x < pp.getDuties().size()); x++) {
////					di = pp.getDuties().get(x);
////					di.tesArt();
////					for (int y = 0; (y < di.getDutyFlights().size()); y++) {
////						fi = di.getDutyFlights().get(y).getFlight().gk;
//////						if (fi.compCvr) {
////							fi.tesArt();
//////						}
////					}
////				}
////			}
//			PairStore.activatePair(pp);
////			pp.adm = BaatarItr.getMainStep() - 1;
//			Pair.postProcess(pp, BaatarItr.getMainStep() - 1);
//			ps = ArrayUtils.add(ps, pp);
//		}
//	}
}
