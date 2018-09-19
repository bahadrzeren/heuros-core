package org.heuros.util;

import java.lang.reflect.Type;

import org.heuros.core.rule.fintf.ImplementationChecker;
import org.heuros.core.rule.fintf.RuleAnnotationGetter;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.intf.RuleImplementation;
import org.heuros.data.model.Duty;
import org.heuros.data.model.LegView;
import org.heuros.rule.DutyRuleContext;
import org.heuros.rule.LazyDutyValidator;
import org.heuros.rule.LegRuleContext;

/**
 * General purpose class used for calling rule specific methods and functional interface implementations.
 * 
 * @author bahadrzeren
 *
 */
public class RuleUtil {

	public static ImplementationChecker<Rule, Class<?>> implChecker = (ruleImpl, intfClass, genericTypes) -> {
		StringBuilder genericDef = new StringBuilder();
		genericDef.append("<");
		genericDef.append(genericTypes[0].getName());
		for (int i = 1; i < genericTypes.length; i++) {
			genericDef.append(", ");
			genericDef.append(genericTypes[i].getName());
		}
		genericDef.append(">");

		for (Type intrface: ruleImpl.getClass().getGenericInterfaces()) {
			if (intrface.getTypeName().startsWith(intfClass.getName())
					&& (intrface.getTypeName().endsWith(genericDef.toString())))
				return true;
		}
		return false;
	};

	public static RuleAnnotationGetter<Rule> ruleAnnotationGetter = (c) -> {
		return c.getClass().getAnnotation(RuleImplementation.class);
	};

	public static LazyDutyValidator lazyDutyValidator = (Duty d,
															LegRuleContext legRuleContext,
															DutyRuleContext dutyRuleContext) -> {
		if (d == null)
			return 0;
		if (d.getLegs() == null)
			return 0;
		if (d.getLegs().size() == 0)
			return 0;
		LegView nl = d.getLegs().get(0);
		if (nl == null)
			return 0;
		LegView pl;
		/*
		 * Duty rule level does not need any HB control therefore -1 is used.
		 */
		int bitwiseValid = dutyRuleContext.getStarterCheckerProxy().canBeStarter(nl);
		if (bitwiseValid > 0) {
			dutyRuleContext.getAggregatorImpl().reset(d);
			dutyRuleContext.getAggregatorImpl().softAppend(d, nl);
			for (int i = 1; i < d.getLegs().size(); i++) {
				pl = nl;
				nl = d.getLegs().get(i);
				bitwiseValid &= legRuleContext.getConnectionCheckerProxy().areConnectable(pl, nl);
				if (bitwiseValid > 0) {
					bitwiseValid &= dutyRuleContext.getExtensibilityCheckerProxy().isExtensible(d);
					if (bitwiseValid > 0) {
						bitwiseValid &= dutyRuleContext.getAppendabilityCheckerProxy().isAppendable(d, nl);
						if (bitwiseValid > 0) {
							dutyRuleContext.getAggregatorImpl().softAppend(d, nl);
						} else
							return 0;
					} else
						return 0;
				} else
					return 0;
			}
			bitwiseValid &= dutyRuleContext.getFinalCheckerProxy().acceptable(d);
		}
		return bitwiseValid;
	};

}
