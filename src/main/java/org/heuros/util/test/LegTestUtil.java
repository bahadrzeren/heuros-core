package org.heuros.util.test;

import java.time.LocalDateTime;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegFactory;
import org.heuros.rule.LegRuleContext;

public class LegTestUtil {

	public static LegFactory legFactory = null;
	public static LegRuleContext legRuleContext = null;

	public static LegRuleContext initializeLegContext(Rule legIntroducer, int numOfBases) {
		LegTestUtil.legFactory = new LegFactory();
		LegTestUtil.legRuleContext = new LegRuleContext(numOfBases);
    	try {
    		LegTestUtil.legRuleContext.registerRule(legIntroducer);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	return LegTestUtil.legRuleContext;
	}

	public static Leg generateLegInstance(int flightNo,
											Airport depAirport,
											Airport arrAirport,
											LocalDateTime sobt,
											LocalDateTime sibt,
											String acType) {
    	Leg legInstance = LegTestUtil.legFactory.generateModel();
    	legInstance.setFlightNo(flightNo);
    	legInstance.setDepAirport(depAirport);
    	legInstance.setArrAirport(arrAirport);
    	legInstance.setDep(depAirport.getCode());
    	legInstance.setArr(arrAirport.getCode());
    	legInstance.setSobt(sobt);
    	legInstance.setSibt(sibt);
    	legInstance.setAcType(acType);

    	if (LegTestUtil.legRuleContext.getIntroducerProxy().introduce(legInstance))
    		return legInstance;
    	return null;
	}
}
