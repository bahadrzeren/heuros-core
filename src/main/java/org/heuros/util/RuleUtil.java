package org.heuros.util;

import java.lang.reflect.Type;

import org.heuros.core.rule.fintf.ImplementationChecker;
import org.heuros.core.rule.fintf.RuleAnnotationGetter;
import org.heuros.core.rule.intf.Rule;
import org.heuros.core.rule.intf.RuleImplementation;

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
}
