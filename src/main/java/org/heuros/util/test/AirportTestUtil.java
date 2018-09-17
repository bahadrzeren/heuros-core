package org.heuros.util.test;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.AirportFactory;
import org.heuros.rule.AirportRuleContext;

public class AirportTestUtil {

	public static AirportFactory apFactory = null;
	public static AirportRuleContext apRuleContext = null;

	public static AirportRuleContext initializeAirportContext(Rule apIntroducer) {
		AirportTestUtil.apFactory = new AirportFactory();
		AirportTestUtil.apRuleContext = new AirportRuleContext();
    	try {
    		AirportTestUtil.apRuleContext.registerRule(apIntroducer);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	return AirportTestUtil.apRuleContext;
	}

	public static Airport generateAirportInstance(String apCode) {
    	Airport apInstance = AirportTestUtil.apFactory.generateModel();
    	apInstance.setCode(apCode);
    	if (AirportTestUtil.apRuleContext.getIntroducerProxy().introduce(apInstance))
    		return apInstance;
    	return null;
	}
}
