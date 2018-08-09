package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;

public class DutyWrapper extends AbstractWrapper<Duty> implements ModelListHolder<Leg> {

	public DutyWrapper(Duty wrappee) {
		super(wrappee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addToTheFront(Leg c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToTheEnd(Leg c) {
		// TODO Auto-generated method stub
		
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
