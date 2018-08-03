package org.heuros.test.rule.repo;

import org.heuros.core.model.Leg;
import org.heuros.core.rule.inf.AbstractRule;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.Introducer;

public class RuleWithoutAnnotation extends AbstractRule
									implements Introducer<Leg>, ConnectionChecker<Leg> {

	@Override
	public boolean areConnectable(Leg prev, Leg next) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean introduce(Leg m) {
		// TODO Auto-generated method stub
		return false;
	}

}
