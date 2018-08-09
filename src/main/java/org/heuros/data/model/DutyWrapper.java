package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;
import org.heuros.rule.DutyRuleContext;

public class DutyWrapper extends AbstractWrapper<Duty> implements ModelListHolder<Leg> {

	public DutyWrapper(DutyRuleContext dutyRuleContext, Duty wrappee) {
		super(dutyRuleContext, wrappee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelListHolder<Leg> addToTheFront(Leg c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelListHolder<Leg> addToTheEnd(Leg c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Leg removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Leg removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
}
