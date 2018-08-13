package org.heuros.test.rule;

import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;

public class LegRuleWithoutAnnotation extends AbstractRule
									implements Introducer<LegModel, LegExtension> {

	@Override
	public boolean introduce(LegModel m, LegExtension e) {
		// TODO Auto-generated method stub
		return false;
	}
}
