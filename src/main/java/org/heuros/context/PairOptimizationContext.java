package org.heuros.context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.Duty;
import org.heuros.data.model.Leg;
import org.heuros.data.repo.AirportRepository;
import org.heuros.data.repo.DutyRepository;
import org.heuros.data.repo.LegRepository;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRegistrationMatchingException;
import org.heuros.rule.AirportRuleContext;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.LegRuleContext;
import org.heuros.rule.PairRuleContext;

/**
 * Facade pattern to be used in Crew Pairing Optimization projects.
 * Includes Airport, Leg, Duty and Pairing related factories, repositories, indexes, rule contexts etc.
 * 
 * @author bahadrzeren
 *
 */
public class PairOptimizationContext {

	private static Logger logger = Logger.getLogger(PairOptimizationContext.class);

	/**
	 * Airport model related classes.
	 */
	private AirportRepository airportRepository = null;
	private AirportRuleContext airportRuleContext = null;

	/**
	 * Flight leg model related classes.
	 */
	private LegRepository legRepository = null;
	private LegRuleContext legRuleContext = null;
	/**
	 * Index class used to obtain next possible leg connections of a particular leg.
	 */
	private OneDimIndexInt<Leg> connectionLegsIndex = null;
	public OneDimIndexInt<Leg> getConnectionLegsIndex() {
		return this.connectionLegsIndex;
	}

	/**
	 * Duty model related classes.
	 */
	private DutyRepository dutyRepository = null;
	private DutyRuleContext dutyRuleContext = null;
	/**
	 * Index class used to obtain duties that include a particular leg.
	 */
	private OneDimIndexInt<Duty> dutyIndexByLegNdx = null;
	/**
	 * Index class used to obtain hb departed duties that include a particular leg.
	 */
	private TwoDimIndexIntXInt<Duty> hbDepDutyIndexByLegNdx = null;
	/**
	 * Index class used to obtain hb departed and hb arrival duties that include a particular leg.
	 */
	private TwoDimIndexIntXInt<Duty> hbDepHbArrDutyIndexByLegNdx = null;
	/**
	 * Index class used to obtain duties that departs from a particular airport and time (hour).
	 */
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByDepAirportNdxBrieftime = null;
	private TwoDimIndexIntXLocalDateTime<Duty> dutyIndexByArrAirportNdxNextBrieftime = null;
	/**
	 * Index class used to obtain hb arrival duties that departs from a particular airport and time (hour).
	 */
	private TwoDimIndexIntXLocalDateTime<Duty> hbArrDutyIndexByDepAirportNdxBrieftime = null;
	public OneDimIndexInt<Duty> getDutyIndexByLegNdx() {
		return dutyIndexByLegNdx;
	}
	public TwoDimIndexIntXInt<Duty> getHbDepDutyIndexByLegNdx() {
		return hbDepDutyIndexByLegNdx;
	}
	public TwoDimIndexIntXInt<Duty> getHbDepHbArrDutyIndexByLegNdx() {
		return hbDepHbArrDutyIndexByLegNdx;
	}
	public TwoDimIndexIntXLocalDateTime<Duty> getDutyIndexByDepAirportNdxBrieftime() {
		return dutyIndexByDepAirportNdxBrieftime;
	}
	public TwoDimIndexIntXLocalDateTime<Duty> getDutyIndexByArrAirportNdxNextBrieftime() {
		return dutyIndexByArrAirportNdxNextBrieftime;
	}
	public TwoDimIndexIntXLocalDateTime<Duty> getHbArrDutyIndexByDepAirportNdxBrieftime() {
		return hbArrDutyIndexByDepAirportNdxBrieftime;
	}

	/**
	 * Pair model related classes.
	 */
	private PairRuleContext pairRuleContext = null;

	public AirportRepository getAirportRepository() {
		return airportRepository;
	}
	public PairOptimizationContext setAirportRepository(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
		return this;
	}
	public AirportRuleContext getAirportRuleContext() {
		return airportRuleContext;
	}
	public PairOptimizationContext setAirportRuleContext(AirportRuleContext airportRuleContext) {
		this.airportRuleContext = airportRuleContext;
		return this;
	}
	public LegRepository getLegRepository() {
		return legRepository;
	}
	public PairOptimizationContext setLegRepository(LegRepository legRepository) {
		this.legRepository = legRepository;
		return this;
	}
	public LegRuleContext getLegRuleContext() {
		return legRuleContext;
	}
	public PairOptimizationContext setLegRuleContext(LegRuleContext legRuleContext) {
		this.legRuleContext = legRuleContext;
		return this;
	}
	public DutyRepository getDutyRepository() {
		return dutyRepository;
	}
	public PairOptimizationContext setDutyRepository(DutyRepository dutyRepository) {
		this.dutyRepository = dutyRepository;
		return this;
	}
	public DutyRuleContext getDutyRuleContext() {
		return dutyRuleContext;
	}
	public PairOptimizationContext setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}
	public PairRuleContext getPairRuleContext() {
		return pairRuleContext;
	}
	public PairOptimizationContext setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

	/**
	 * Registers all the rule implementation classes provided by the list parameter on the related ruleContext classes.
	 * 
	 * @param rules List of rule implementation classes to be registered.
	 * @throws RuleRegistrationMatchingException
	 * @throws RuleAnnotationIsMissing
	 */
	public void registerRules(List<Rule> rules) throws RuleRegistrationMatchingException, RuleAnnotationIsMissing {
		for (Rule r : rules) {
			try {
				int numOfRegistrations = 0;
				numOfRegistrations += this.airportRuleContext.registerRule(r);
				numOfRegistrations += this.legRuleContext.registerRule(r);
				numOfRegistrations += this.dutyRuleContext.registerRule(r);
				numOfRegistrations += this.pairRuleContext.registerRule(r);
				if (numOfRegistrations != r.getClass().getGenericInterfaces().length)
					throw new RuleRegistrationMatchingException("Rule imlementations and number of registrations do not match!");
			} catch (RuleRegistrationMatchingException ex) {
				logger.error(ex);
				throw ex;
			} catch (RuleAnnotationIsMissing ex) {
				logger.error("RuleImplementation annotation is missing for " + r.getClass().getName() + ".");
				logger.error(ex);
				throw ex;
			}
		}
	}

	/**
	 * Gets airport by airport code.
	 * If not found a new one is created and registered on the airportRepository.
	 * 
	 * @param airportCode Code of the airport to be fetched or created/registered.
	 * @return Airport instance requested.
	 */
	private Airport getAirport(String airportCode) {
		Airport ap = this.airportRepository.getAirport(airportCode);
		if (ap == null) {
			ap = Airport.newInstance(airportCode);
			if (this.airportRuleContext.getIntroducerProxy().introduce(ap))
				this.airportRepository.addToRepo(ap);
			else
				return null;
		}
		return ap;
	}

	/**
	 * Registers the leg instance on the repository.
	 * 
	 * @param l Leg instance to register.
	 * @return
	 */
	private boolean registerLeg(Leg l) {
		if (this.legRuleContext.getIntroducerProxy().introduce(l)) {
			this.legRepository.addToRepo(l);
			return true;
		}
		return false;
	}

	/**
	 * Registers leg instances and airports where the legs arrives or departs and generates necessary index class instances to be used later on.
	 * 
	 * @param legs Leg instances to register.
	 * @param dataPeriodStartInc Minimum allowed departure dateTime of the legs to be registered.
	 * @param maxLegConnectionTimeInMins Maximum leg connection time for legs to be connected each other in a duty.
	 */
	public void registerAirportsAndLegs(List<Leg> legs, LocalDateTime dataPeriodStartInc, int maxLegConnectionTimeInMins) {
		legs.forEach((l) -> {
			l.setDepAirport(this.getAirport(l.getDep()));
			l.setArrAirport(this.getAirport(l.getArr()));
			if (!this.registerLeg(l)) {
				if (!l.getSobt().isBefore(dataPeriodStartInc))
					logger.warn("Leg " + l + " is ignored!");	//	Flight legs that are not going to be used.
			}
		});

		List<Leg> ls = this.legRepository.getModels();

		this.connectionLegsIndex = new OneDimIndexInt<Leg>(new Leg[ls.size()][0]);

		int numOfActiveLegs = 0;
		int numOfConnectionsChecked = 0;
		int numOfConnectionsIndexed = 0;
		for (int i = 0; i < ls.size(); i++) {
			Leg pl = ls.get(i);
			for (int j = i + 1; j < ls.size(); j++) {
				Leg nl = ls.get(j);

//if ((pl.getNdx() == 900) && (nl.getNdx() == 1079))
//	System.out.println();

				if (pl.getArrAirport().getNdx() == nl.getDepAirport().getNdx()) {
					int connTime = (int) ChronoUnit.MINUTES.between(pl.getSibt(), nl.getSobt());
					if (connTime > maxLegConnectionTimeInMins)
						break;
					numOfConnectionsChecked++;
					if (legRuleContext.getConnectionCheckerProxy().areConnectable(pl, nl) > 0) {
						this.connectionLegsIndex.add(pl.getNdx(), nl);
						numOfConnectionsIndexed++;
					}
				}
			}
			if (pl.isCover())
				numOfActiveLegs++;
		}
		PairOptimizationContext.logger.info(numOfActiveLegs + " number of active legs found!");
		PairOptimizationContext.logger.info(numOfConnectionsChecked + " number of leg connections are checked!");
		PairOptimizationContext.logger.info(numOfConnectionsIndexed + " number of leg connections are found!");
		PairOptimizationContext.logger.info(this.connectionLegsIndex.getNumOfIndexedElements() + " number of leg connections are indexed!");
	}

	/**
	 * Registers duty instances on the dutyRepository and generates necessary index class instances to be used later on.
	 * 
	 * @param duties Duty instances to register.
	 */
	public void registerDuties(List<Duty> duties, int numOfBases) {
		List<Leg> legs = this.legRepository.getModels();
		List<Airport> airports = this.airportRepository.getModels();

		int secondNdxSize = 50 * 24;	//	In a one month period there is around flight legs of 50 days.

		this.dutyIndexByLegNdx = new OneDimIndexInt<Duty>(new Duty[legs.size()][0]);
		this.hbDepDutyIndexByLegNdx = new TwoDimIndexIntXInt<Duty>(new Duty[numOfBases][legs.size()][0]);
		this.hbDepHbArrDutyIndexByLegNdx = new TwoDimIndexIntXInt<Duty>(new Duty[numOfBases][legs.size()][0]);
		this.dutyIndexByDepAirportNdxBrieftime = new TwoDimIndexIntXLocalDateTime<Duty>(new Duty[airports.size()][secondNdxSize][0]);
		this.dutyIndexByArrAirportNdxNextBrieftime = new TwoDimIndexIntXLocalDateTime<Duty>(new Duty[airports.size()][secondNdxSize][0]);
		this.hbArrDutyIndexByDepAirportNdxBrieftime = new TwoDimIndexIntXLocalDateTime<Duty>(new Duty[airports.size()][secondNdxSize][0]);

		/*
		 * Choose first homebase brieftime for the index roots!
		 */
		this.dutyIndexByDepAirportNdxBrieftime.setRootNdxD(duties.get(0).getBriefTime(0).minusHours(1));
		this.dutyIndexByArrAirportNdxNextBrieftime.setRootNdxD(duties.get(0).getBriefTime(0).minusHours(1));
		this.hbArrDutyIndexByDepAirportNdxBrieftime.setRootNdxD(duties.get(0).getBriefTime(0).minusHours(1));

		duties.forEach((d) -> {
			int depAirportNdx = d.getFirstDepAirport().getNdx();
			int arrAirportNdx = d.getLastArrAirport().getNdx();
			int depHbNdx = d.getFirstDepAirport().getHbNdx();
			int arrHbNdx = d.getLastArrAirport().getHbNdx();
			boolean hbDep = d.getFirstDepAirport().isAnyHb();
			boolean hbArr = d.getLastArrAirport().isAnyHb();
			d.getLegs().forEach((l) -> {
				Leg leg = (Leg) l;
				leg.incNumOfDutiesIncludes();
				if (hbDep)
					leg.incNumOfDutiesIncludesHbDep();
				else
					leg.incNumOfDutiesIncludesNonHbDep();
				if (hbArr)
					leg.incNumOfDutiesIncludesHbArr();
				else
					leg.incNumOfDutiesIncludesNonHbArr();
				this.dutyIndexByLegNdx.add(leg.getNdx(), d);
				if (hbDep) {
					this.hbDepDutyIndexByLegNdx.add(depHbNdx, leg.getNdx(), d);
					if (hbArr && (depHbNdx == arrHbNdx)) {
						this.hbDepHbArrDutyIndexByLegNdx.add(arrHbNdx, leg.getNdx(), d);
					}
				}
			});

			/*
			 * Set leg related accumulators.
			 */
			d.getLegs().forEach((l) -> {
				d.incTotalNumOfIncludingDutiesOfTheSameLegs(l.getNumOfDutiesIncludes());
			});

			this.dutyRepository.addToRepo(d);
			/*
			 * Choose first homebase brieftime for the index roots!
			 */
			this.dutyIndexByDepAirportNdxBrieftime.add(depAirportNdx, d.getBriefTime(0), d);
			this.dutyIndexByArrAirportNdxNextBrieftime.add(arrAirportNdx, d.getNextBriefTime(0), d);
			if (hbArr) {
				this.hbArrDutyIndexByDepAirportNdxBrieftime.add(depAirportNdx, d.getBriefTime(0), d);
			}
		}); 

		PairOptimizationContext.logger.info("All duties are registered and indexed!");
	}
}
