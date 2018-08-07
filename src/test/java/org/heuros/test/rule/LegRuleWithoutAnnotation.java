package org.heuros.test.rule;

import org.heuros.core.data.model.Leg;
import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;

public class LegRuleWithoutAnnotation extends AbstractRule
									implements Introducer<Leg> {

	@Override
	public boolean introduce(Leg m) {
		// TODO Auto-generated method stub
		return false;
	}
}
