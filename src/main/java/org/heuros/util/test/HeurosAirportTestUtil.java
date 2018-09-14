package org.heuros.util.test;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.AirportFactory;
import org.heuros.rule.AirportRuleContext;

public class HeurosAirportTestUtil {

	private static AirportFactory apFactory = null;
	private static AirportRuleContext apRuleContext = null;

	public static AirportRuleContext initializeAirportContext(Rule apIntroducer) {
		HeurosAirportTestUtil.apFactory = new AirportFactory();
		HeurosAirportTestUtil.apRuleContext = new AirportRuleContext();
    	try {
    		HeurosAirportTestUtil.apRuleContext.registerRule(apIntroducer);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	return HeurosAirportTestUtil.apRuleContext;
	}

	public static Airport generateAirportInstance(String apCode) {
    	Airport apInstance = HeurosAirportTestUtil.apFactory.generateModel();
    	apInstance.setCode(apCode);
    	if (HeurosAirportTestUtil.apRuleContext.getIntroducerProxy().introduce(apInstance))
    		return apInstance;
    	return null;
	}
}
