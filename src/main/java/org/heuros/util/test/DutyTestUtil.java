package org.heuros.util.test;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyFactory;
import org.heuros.data.model.Leg;
import org.heuros.rule.DutyRuleContext;

public class DutyTestUtil {

	public static DutyFactory dutyFactory = null;
	public static DutyRuleContext dutyRuleContext = null;

	public static DutyRuleContext initializeDutyContext(Rule dutyLegAggregator, int numOfBases) {
		DutyTestUtil.dutyFactory = new DutyFactory(numOfBases);
		DutyTestUtil.dutyRuleContext = new DutyRuleContext(numOfBases);
		try {
			DutyTestUtil.dutyRuleContext.registerRule(dutyLegAggregator);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	return DutyTestUtil.dutyRuleContext;
	}

	public static Duty generateDutyInstance(Leg[] legs) {
    	Duty dutyInstance = DutyTestUtil.dutyFactory.generateModel();
    	for (Leg leg: legs) {
    		DutyTestUtil.dutyRuleContext.getAggregatorProxy().append(dutyInstance, leg);
		}
    	return dutyInstance;
	}

	public static boolean reCalculateDutyInstance(Duty d) {
		return DutyTestUtil.dutyRuleContext.getAggregatorProxy().reCalculate(d);
	}
}
