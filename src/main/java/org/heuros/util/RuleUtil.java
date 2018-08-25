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
	};

}
