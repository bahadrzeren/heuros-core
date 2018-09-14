package org.heuros.util.test;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyFactory;
import org.heuros.data.model.Leg;
import org.heuros.rule.DutyRuleContext;

public class HeurosDutyTestUtil {

	private static DutyFactory dutyFactory = null;
	private static DutyRuleContext dutyRuleContext = null;

	public static DutyRuleContext initializeDutyContext(Rule dutyLegAggregator, int numOfBases) {
		HeurosDutyTestUtil.dutyFactory = new DutyFactory(numOfBases);
		HeurosDutyTestUtil.dutyRuleContext = new DutyRuleContext(numOfBases);
		try {
			HeurosDutyTestUtil.dutyRuleContext.registerRule(dutyLegAggregator);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	return HeurosDutyTestUtil.dutyRuleContext;
	}

	public static Duty generateDutyInstance(Leg[] legs) {
    	Duty dutyInstance = HeurosDutyTestUtil.dutyFactory.generateModel();
    	for (Leg leg: legs) {
    		HeurosDutyTestUtil.dutyRuleContext.getAggregatorProxy().append(dutyInstance, leg);
		}
    	return dutyInstance;
	}

	public static boolean reCalculateDutyInstance(Duty d) {
		return HeurosDutyTestUtil.dutyRuleContext.getAggregatorProxy().reCalculate(d);
	}
}
