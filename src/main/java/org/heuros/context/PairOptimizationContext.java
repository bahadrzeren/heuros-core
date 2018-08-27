package org.heuros.context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.data.ndx.TwoDimIndexIntXLocalDateTime;
import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.AirportFactory;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyFactory;
import org.heuros.data.model.DutyView;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegFactory;
import org.heuros.data.model.PairFactory;
import org.heuros.data.repo.AirportRepository;
import org.heuros.data.repo.DutyRepository;
import org.heuros.data.repo.LegRepository;
import org.heuros.exception.RuleAnnotationIsMissing;
import org.heuros.exception.RuleRegistrationMatchingException;
import org.heuros.rule.AirportRuleContext;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.LegRuleContext;
import org.heuros.rule.PairRuleContext;

public class PairOptimizationContext {

	private static Logger logger = Logger.getLogger(PairOptimizationContext.class);

	private AirportFactory airportFactory = null;
	private AirportRepository airportRepository = null;
	private AirportRuleContext airportRuleContext = null;

	private LegFactory legFactory = null;
	private LegRepository legRepository = null;
	private LegRuleContext legRuleContext = null;
	private OneDimIndexInt<Leg> connectionLegsIndex = null;
	public OneDimIndexInt<Leg> getConnectionLegsIndex() {
		return this.connectionLegsIndex;
	}

	private DutyFactory dutyFactory = null;
	private DutyRepository dutyRepository = null;
	private DutyRuleContext dutyRuleContext = null;
	/*
	 * TODO HB impl will be changed!
	 */
	private OneDimIndexInt<DutyView> dutyIndexByLegNdx = null;
	private OneDimIndexInt<DutyView> hbDepDutyIndexByLegNdx = null;
	private OneDimIndexInt<DutyView> hbDepHbArrDutyIndexByLegNdx = null;
	private TwoDimIndexIntXLocalDateTime<DutyView> dutyIndexByDepAirportNdxBrieftime = null;
	private TwoDimIndexIntXLocalDateTime<DutyView> hbArrDutyIndexByDepAirportNdxBrieftime = null;
	public OneDimIndexInt<DutyView> getDutyIndexByLegNdx() {
		return dutyIndexByLegNdx;
	}
	public OneDimIndexInt<DutyView> getHbDepDutyIndexByLegNdx() {
		return hbDepDutyIndexByLegNdx;
	}
	public OneDimIndexInt<DutyView> getHbDepHbArrDutyIndexByLegNdx() {
		return hbDepHbArrDutyIndexByLegNdx;
	}
	public TwoDimIndexIntXLocalDateTime<DutyView> getDutyIndexByDepAirportNdxBrieftime() {
		return dutyIndexByDepAirportNdxBrieftime;
	}
	public TwoDimIndexIntXLocalDateTime<DutyView> getHbArrDutyIndexByDepAirportNdxBrieftime() {
		return hbArrDutyIndexByDepAirportNdxBrieftime;
	}

	private PairFactory pairFactory = null;
	private PairRuleContext pairRuleContext = null;

	public AirportFactory getAirportFactory() {
		return airportFactory;
	}
	public PairOptimizationContext setAirportFactory(AirportFactory airportFactory) {
		this.airportFactory = airportFactory;
		return this;
	}
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
	public LegFactory getLegFactory() {
		return legFactory;
	}
	public PairOptimizationContext setLegFactory(LegFactory legFactory) {
		this.legFactory = legFactory;
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
	public DutyFactory getDutyFactory() {
		return dutyFactory;
	}
	public PairOptimizationContext setDutyFactory(DutyFactory dutyFactory) {
		this.dutyFactory = dutyFactory;
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
	public PairFactory getPairFactory() {
		return pairFactory;
	}
	public PairOptimizationContext setPairFactory(PairFactory pairFactory) {
		this.pairFactory = pairFactory;
		return this;
	}
	public PairRuleContext getPairRuleContext() {
		return pairRuleContext;
	}
	public PairOptimizationContext setPairRuleContext(PairRuleContext pairRuleContext) {
		this.pairRuleContext = pairRuleContext;
		return this;
	}

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

	private Airport getAirport(String airportCode) {
		Airport ap = this.airportRepository.getAirport(airportCode);
		if (ap == null) {
			ap = this.airportFactory.generateModel(airportCode);
			if (this.airportRuleContext.getIntroducerProxy().introduce(ap))
				this.airportRepository.addToRepo(ap);
			else
				return null;
		}
		return ap;
	}

	private boolean registerLeg(Leg l) {
		if (this.legRuleContext.getIntroducerProxy().introduce(l)) {
			this.legRepository.addToRepo(l);
			return true;
		}
		return false;
	}

	public void registerAirportsAndLegs(List<Leg> legs, LocalDateTime dataPeriodStartInc, int maxLegConnectionTimeInMins) {
		legs.forEach((l) -> {
			l.setDepAirport(this.getAirport(l.getDep()));
			l.setArrAirport(this.getAirport(l.getArr()));
			if (!this.registerLeg(l)) {
				if (!l.getSobt().isBefore(dataPeriodStartInc))
					logger.warn("Leg " + l + " is ignored!");	//	Flight legs that are not going to be used.
			}
		});
		this.buildLegConnectionIndex(maxLegConnectionTimeInMins);
	}

	private void buildLegConnectionIndex(int maxLegConnectionTimeInMins) {
		List<Leg> legs = this.legRepository.getModels();

		this.connectionLegsIndex = new OneDimIndexInt<Leg>(new Leg[legs.size()][0]);

		int numOfActiveLegs = 0;
		int numOfConnectionsChecked = 0;
		int numOfConnectionsIndexed = 0;
		for (int i = 0; i < legs.size(); i++) {
			Leg pl = legs.get(i);
//if (pl.getNdx() == 991)
//System.out.println(pl);
			for (int j = i + 1; j < legs.size(); j++) {
				Leg nl = legs.get(j);
				if (pl.getArrAirport().getNdx() == nl.getDepAirport().getNdx()) {
					int connTime = (int) ChronoUnit.MINUTES.between(pl.getSibt(), nl.getSobt());
					if (connTime > maxLegConnectionTimeInMins)
						break;
					numOfConnectionsChecked++;
					if (legRuleContext.getConnectionCheckerProxy().areConnectable(pl, nl)) {
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

	public void registerDuties(List<Duty> duties) {
		List<Leg> legs = this.legRepository.getModels();
		List<Airport> airports = this.airportRepository.getModels();

		int secondNdxSize = 50 * 24;	//	In a one month period there is around flight legs of 50 days.

		this.dutyIndexByLegNdx = new OneDimIndexInt<DutyView>(new DutyView[legs.size()][0]);
		this.hbDepDutyIndexByLegNdx = new OneDimIndexInt<DutyView>(new DutyView[legs.size()][0]);
		this.hbDepHbArrDutyIndexByLegNdx = new OneDimIndexInt<DutyView>(new DutyView[legs.size()][0]);
		this.dutyIndexByDepAirportNdxBrieftime = new TwoDimIndexIntXLocalDateTime<DutyView>(new DutyView[airports.size()][secondNdxSize][0]);
		this.hbArrDutyIndexByDepAirportNdxBrieftime = new TwoDimIndexIntXLocalDateTime<DutyView>(new DutyView[airports.size()][secondNdxSize][0]);

		/*
		 * TODO HB impl will be changed!
		 */
		this.dutyIndexByDepAirportNdxBrieftime.setRootNdxD(duties.get(0).getBriefTimeHb().minusHours(1));
		this.hbArrDutyIndexByDepAirportNdxBrieftime.setRootNdxD(duties.get(0).getBriefTimeHb().minusHours(1));

		duties.forEach((d) -> {
			/*
			 * TODO HB impl will be changed!
			 */
			int depAirportNdx = d.getFirstDepAirport().getNdx();
			boolean hbDep = d.getFirstDepAirport().isHb();
			boolean hbArr = d.getLastArrAirport().isHb();
			d.getLegs().forEach((l) -> {
				Leg leg = (Leg) l;
				leg.incNumOfDutiesIncludes();
				/*
				 * TODO HB impl will be changed!
				 */
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
					this.hbDepDutyIndexByLegNdx.add(leg.getNdx(), d);
					if (hbArr) {
						this.hbDepHbArrDutyIndexByLegNdx.add(leg.getNdx(), d);
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
			 * TODO HB impl will be changed!
			 */
			this.dutyIndexByDepAirportNdxBrieftime.add(depAirportNdx, d.getBriefTimeHb(), d);
			if (hbArr) {
				this.hbArrDutyIndexByDepAirportNdxBrieftime.add(depAirportNdx, d.getBriefTimeHb(), d);
			}
		}); 

		PairOptimizationContext.logger.info("All duties are registered and indexed!");
	}
}
