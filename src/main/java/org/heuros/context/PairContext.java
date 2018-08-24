package org.heuros.context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.AirportFactory;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyFactory;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegFactory;
import org.heuros.data.model.LegView;
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

public class PairContext {

	private static Logger logger = Logger.getLogger(PairContext.class);

	private AirportFactory airportFactory;
	private AirportRepository airportRepository;
	private AirportRuleContext airportRuleContext;

	private LegFactory legFactory;
	private LegRepository legRepository;
	private LegRuleContext legRuleContext;
	private OneDimIndexInt<Leg> connectionLegsIndex;
	public OneDimIndexInt<Leg> getConnectionLegsIndex() {
		return this.connectionLegsIndex;
	}

	private DutyFactory dutyFactory;
	private DutyRepository dutyRepository;
	private DutyRuleContext dutyRuleContext;

	private PairFactory pairFactory;
	private PairRuleContext pairRuleContext;

	public AirportFactory getAirportFactory() {
		return airportFactory;
	}
	public PairContext setAirportFactory(AirportFactory airportFactory) {
		this.airportFactory = airportFactory;
		return this;
	}
	public AirportRepository getAirportRepository() {
		return airportRepository;
	}
	public PairContext setAirportRepository(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
		return this;
	}
	public AirportRuleContext getAirportRuleContext() {
		return airportRuleContext;
	}
	public PairContext setAirportRuleContext(AirportRuleContext airportRuleContext) {
		this.airportRuleContext = airportRuleContext;
		return this;
	}
	public LegFactory getLegFactory() {
		return legFactory;
	}
	public PairContext setLegFactory(LegFactory legFactory) {
		this.legFactory = legFactory;
		return this;
	}
	public LegRepository getLegRepository() {
		return legRepository;
	}
	public PairContext setLegRepository(LegRepository legRepository) {
		this.legRepository = legRepository;
		return this;
	}
	public LegRuleContext getLegRuleContext() {
		return legRuleContext;
	}
	public PairContext setLegRuleContext(LegRuleContext legRuleContext) {
		this.legRuleContext = legRuleContext;
		return this;
	}
	public DutyFactory getDutyFactory() {
		return dutyFactory;
	}
	public PairContext setDutyFactory(DutyFactory dutyFactory) {
		this.dutyFactory = dutyFactory;
		return this;
	}
	public DutyRepository getDutyRepository() {
		return dutyRepository;
	}
	public PairContext setDutyRepository(DutyRepository dutyRepository) {
		this.dutyRepository = dutyRepository;
		return this;
	}
	public DutyRuleContext getDutyRuleContext() {
		return dutyRuleContext;
	}
	public PairContext setDutyRuleContext(DutyRuleContext dutyRuleContext) {
		this.dutyRuleContext = dutyRuleContext;
		return this;
	}
	public PairFactory getPairFactory() {
		return pairFactory;
	}
	public PairContext setPairFactory(PairFactory pairFactory) {
		this.pairFactory = pairFactory;
		return this;
	}
	public PairRuleContext getPairRuleContext() {
		return pairRuleContext;
	}
	public PairContext setPairRuleContext(PairRuleContext pairRuleContext) {
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

	public void registerAirportsAndLegs(List<Leg> legs, LocalDateTime dataPeriodStartInc) {
		legs.forEach((l) -> {
			l.setDepAirport(this.getAirport(l.getDep()));
			l.setArrAirport(this.getAirport(l.getArr()));
			if (!this.registerLeg(l)) {
				if (!l.getSobt().isBefore(dataPeriodStartInc))
					logger.warn("Leg " + l + " is not registered!");	//	Flight legs that are not going to be used.
			}
		});
	}

	public void buildLegConnectionIndex(int maxLegConnectionTimeInMins) {
		List<Leg> legs = this.legRepository.getModels();

		this.connectionLegsIndex = new OneDimIndexInt<Leg>(new Leg[legs.size()][0]);

		int numOfConnectionsChecked = 0;
		int numOfConnectionsIndexed = 0;
		for (int i = 0; i < legs.size(); i++) {
			Leg pl = legs.get(i);
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
		}
		PairContext.logger.info(numOfConnectionsChecked + " numbers of leg connections are checked!");
		PairContext.logger.info(numOfConnectionsIndexed + " numbers of leg connections are found!");
		PairContext.logger.info(this.connectionLegsIndex.getNumOfIndexedElements() + " numbers of leg connections are indexed!");
	}

	/*
	 * TODO HB impl will be changed!
	 */
	public boolean reValidateDuty(Duty d) {
		if (d == null)
			return false;
		if (d.getLegs() == null)
			return false;
		if (d.getLegs().size() == 0)
			return false;
		LegView nl = d.getLegs().get(0);
		if (nl == null)
			return false;
		LegView pl;
		if (dutyRuleContext.getStarterCheckerProxy().canBeStarter(nl)) {
			dutyRuleContext.getAggregatorImpl().reset(d);
			dutyRuleContext.getAggregatorImpl().softAppend(d, nl);
			for (int i = 1; i < d.getLegs().size(); i++) {
				pl = nl;
				nl = d.getLegs().get(i);
				if (legRuleContext.getConnectionCheckerProxy().areConnectable(pl, nl)) {
					if (dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d)) {
						if (dutyRuleContext.getAppendabilityCheckerProxy().isAppendable(d, nl)) {
							dutyRuleContext.getAggregatorImpl().softAppend(d, nl);
						} else
							return false;
					} else
						return false;
				} else
					return false;
			}
			if (dutyRuleContext.getValidatorProxy().isValid(d))
				return true;
		}
		return false;
	}
}
