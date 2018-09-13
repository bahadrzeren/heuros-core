package org.heuros.util.test;

import java.time.LocalDateTime;
import java.time.Month;

import org.heuros.core.rule.intf.Rule;
import org.heuros.data.model.Airport;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegFactory;
import org.heuros.rule.LegRuleContext;

public class HeurosLegTestUtil {

	private static LegFactory legFactory = null;
	private static LegRuleContext legRuleContext = null;

	public static boolean initializeLegContext(Rule legIntroducer, int numOfBases) {
		HeurosLegTestUtil.legFactory = new LegFactory();
		HeurosLegTestUtil.legRuleContext = new LegRuleContext(numOfBases);
    	try {
    		HeurosLegTestUtil.legRuleContext.registerRule(legIntroducer);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}
    	return true;
	}

	public static Leg generateLegInstance(int flightNo,
											Airport depAirport,
											Airport arrAirport,
											LocalDateTime sobt,
											LocalDateTime sibt,
											String acType) {
    	Leg legInstance = HeurosLegTestUtil.legFactory.generateModel();
    	legInstance.setFlightNo(flightNo);
    	legInstance.setDepAirport(depAirport);
    	legInstance.setArrAirport(arrAirport);
    	legInstance.setSobt(sobt);
    	legInstance.setSibt(sibt);
    	legInstance.setAcType(acType);

    	if (HeurosLegTestUtil.legRuleContext.getIntroducerProxy().introduce(legInstance))
    		return legInstance;
    	return null;
	}

	private static DailyLegsTest[] dailyLegs = null;
	public static DailyLegsTest[] getTestLegs(int numOfDays,
	    	Airport apHb1,
	    	Airport apHb2,
	    	Airport apDom1,
	    	Airport apDom2,
//	    	Airport apDom3,
//	    	Airport apDom4,
	    	Airport apInt1,
	    	Airport apInt2,
//	    	Airport apInt3,
//	    	Airport apInt4,
	    	Airport apEr1,
	    	Airport apEr2
			) {
		if (HeurosLegTestUtil.dailyLegs == null) {
			HeurosLegTestUtil.dailyLegs = new DailyLegsTest[numOfDays];
			for (int i = 0; i < numOfDays; i++) {
				HeurosLegTestUtil.dailyLegs[i] = new DailyLegsTest();
				HeurosLegTestUtil.dailyLegs[i].setHb1_Hb1ToDom1_1(HeurosLegTestUtil.generateLegInstance(101, apHb1, apDom1, LocalDateTime.of(2014, Month.JANUARY, 1, 10, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 11, 30), "320"));	//	IST-DOM	90
				HeurosLegTestUtil.dailyLegs[i].setHb1_Dom1ToHb1_1(HeurosLegTestUtil.generateLegInstance(102, apDom1, apHb1, LocalDateTime.of(2014, Month.JANUARY, 1, 12, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 14, 0), "320"));	//	DOM-IST	90
				HeurosLegTestUtil.dailyLegs[i].setHb1_Hb1ToInt1_1(HeurosLegTestUtil.generateLegInstance(103, apHb1, apInt1, LocalDateTime.of(2014, Month.JANUARY, 1, 11, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 14, 0), "320"));	//	IST-INT	180
				HeurosLegTestUtil.dailyLegs[i].setHb1_Int1ToHb1_1(HeurosLegTestUtil.generateLegInstance(104, apInt1, apHb1, LocalDateTime.of(2014, Month.JANUARY, 1, 14, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 17, 40), "320"));//	INT-IST	190
				HeurosLegTestUtil.dailyLegs[i].setHb2_Hb2ToDom1_1(HeurosLegTestUtil.generateLegInstance(101, apHb2, apDom1, LocalDateTime.of(2014, Month.JANUARY, 1, 10, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 11, 20), "320"));	//	SAW-DOM	80
				HeurosLegTestUtil.dailyLegs[i].setHb2_Dom1ToHb2_1(HeurosLegTestUtil.generateLegInstance(102, apDom1, apHb2, LocalDateTime.of(2014, Month.JANUARY, 1, 12, 20), LocalDateTime.of(2014, Month.JANUARY, 1, 13, 40), "320"));//	DOM-SAW	80
				HeurosLegTestUtil.dailyLegs[i].setHb2_Hb2ToInt1_1(HeurosLegTestUtil.generateLegInstance(103, apHb2, apInt1, LocalDateTime.of(2014, Month.JANUARY, 1, 11, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 13, 55), "320"));	//	SAW-INT	175
				HeurosLegTestUtil.dailyLegs[i].setHb2_Int1ToHb2_1(HeurosLegTestUtil.generateLegInstance(104, apInt1, apHb2, LocalDateTime.of(2014, Month.JANUARY, 1, 14, 25), LocalDateTime.of(2014, Month.JANUARY, 1, 17, 30), "320"));//	INT-SAW	185
				HeurosLegTestUtil.dailyLegs[i].setHb1_Dom1ToInt2_1(HeurosLegTestUtil.generateLegInstance(105, apDom1, apInt2, LocalDateTime.of(2014, Month.JANUARY, 1, 12, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 15, 50), "320"));//	DOM-INT	190
				HeurosLegTestUtil.dailyLegs[i].setHb1_Int2ToDom1_1(HeurosLegTestUtil.generateLegInstance(106, apInt2, apDom1, LocalDateTime.of(2014, Month.JANUARY, 1, 16, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 20, 0), "320"));	//	INT-DOM	200
				HeurosLegTestUtil.dailyLegs[i].setHb1_Dom1ToHb1_2(HeurosLegTestUtil.generateLegInstance(107, apDom1, apHb1, LocalDateTime.of(2014, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 22, 0), "320"));	//	DOM-IST	90
				HeurosLegTestUtil.dailyLegs[i].setHb1_Int1ToDom2_1(HeurosLegTestUtil.generateLegInstance(108, apInt1, apDom2, LocalDateTime.of(2014, Month.JANUARY, 1, 14, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 19, 0), "320"));	//	INT-DOM	260
				HeurosLegTestUtil.dailyLegs[i].setHb1_Dom2ToInt1_1(HeurosLegTestUtil.generateLegInstance(109, apDom2, apInt1, LocalDateTime.of(2014, Month.JANUARY, 1, 19, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 23, 40), "320"));//	DOM-INT	250
				HeurosLegTestUtil.dailyLegs[i].setHb1_Int1ToHb1_2(HeurosLegTestUtil.generateLegInstance(110, apInt1, apHb1, LocalDateTime.of(2014, Month.JANUARY, 2, 0, 30), LocalDateTime.of(2014, Month.JANUARY, 2, 3, 40), "320"));	//	INT-IST	190
				HeurosLegTestUtil.dailyLegs[i].setHb2_Dom1ToInt2_1(HeurosLegTestUtil.generateLegInstance(111, apDom1, apInt2, LocalDateTime.of(2014, Month.JANUARY, 1, 12, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 15, 50), "320"));//	DOM-INT	190
				HeurosLegTestUtil.dailyLegs[i].setHb2_Int2ToDom1_1(HeurosLegTestUtil.generateLegInstance(112, apInt2, apDom1, LocalDateTime.of(2014, Month.JANUARY, 1, 16, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 20, 0), "320"));	//	INT-DOM	200
				HeurosLegTestUtil.dailyLegs[i].setHb2_Dom1ToHb2_2(HeurosLegTestUtil.generateLegInstance(113, apDom1, apHb2, LocalDateTime.of(2014, Month.JANUARY, 1, 20, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 22, 0), "320"));	//	DOM-SAW	90
				HeurosLegTestUtil.dailyLegs[i].setHb2_Int1ToDom2_1(HeurosLegTestUtil.generateLegInstance(114, apInt1, apDom2, LocalDateTime.of(2014, Month.JANUARY, 1, 14, 40), LocalDateTime.of(2014, Month.JANUARY, 1, 19, 0), "320"));	//	INT-DOM	260
				HeurosLegTestUtil.dailyLegs[i].setHb2_Dom2ToInt1_1(HeurosLegTestUtil.generateLegInstance(115, apDom2, apInt1, LocalDateTime.of(2014, Month.JANUARY, 1, 19, 30), LocalDateTime.of(2014, Month.JANUARY, 1, 23, 40), "320"));//	DOM-INT	250
				HeurosLegTestUtil.dailyLegs[i].setHb2_Int1ToHb2_2(HeurosLegTestUtil.generateLegInstance(116, apInt1, apHb2, LocalDateTime.of(2014, Month.JANUARY, 2, 0, 30), LocalDateTime.of(2014, Month.JANUARY, 2, 3, 40), "320"));	//	INT-SAW	190
				HeurosLegTestUtil.dailyLegs[i].setHb1_Hb1ToEr2_1(HeurosLegTestUtil.generateLegInstance(117, apHb1, apEr2, LocalDateTime.of(2014, Month.JANUARY, 1, 9, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 20, 0), "320"));	//	IST-ER	660
				HeurosLegTestUtil.dailyLegs[i].setHb1_Er2ToHb1_1(HeurosLegTestUtil.generateLegInstance(118, apEr2, apHb1, LocalDateTime.of(2014, Month.JANUARY, 1, 21, 0), LocalDateTime.of(2014, Month.JANUARY, 2, 9, 0), "320"));	//	ER-IST	720
				HeurosLegTestUtil.dailyLegs[i].setHb2_Hb1ToEr1_1(HeurosLegTestUtil.generateLegInstance(119, apHb1, apEr1, LocalDateTime.of(2014, Month.JANUARY, 1, 13, 0), LocalDateTime.of(2014, Month.JANUARY, 1, 20, 0), "320"));	//	IST-ER	420
				HeurosLegTestUtil.dailyLegs[i].setHb2_Er1ToHb1_1(HeurosLegTestUtil.generateLegInstance(120, apEr1, apHb1, LocalDateTime.of(2014, Month.JANUARY, 1, 21, 0), LocalDateTime.of(2014, Month.JANUARY, 2, 4, 0), "320"));	//	ER-IST	420
			}
		}
		return HeurosLegTestUtil.dailyLegs;
	}
}
