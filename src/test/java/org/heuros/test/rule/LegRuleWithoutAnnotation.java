package org.heuros.test.rule;

import org.heuros.core.rule.intf.Introducer;
import org.heuros.data.model.Leg;

public class LegRuleWithoutAnnotation implements Introducer<Leg> {

	@Override
	public boolean introduce(Leg m) {
		return false;
	}
}
