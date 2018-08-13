package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegWrapper;

public class LegRuleWithoutAnnotation extends AbstractRule
									implements Introducer<LegWrapper, Leg> {

	@Override
	public boolean introduce(LegWrapper m) {
		// TODO Auto-generated method stub
		return false;
	}
}
