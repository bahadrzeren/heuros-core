package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;
import org.heuros.rule.PairRuleContext;

public class PairWrapper extends AbstractWrapper<Pair> implements ModelListHolder<Duty> {

	public PairWrapper(PairRuleContext pairRuleContext, Pair wrappee) {
		super(pairRuleContext, wrappee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelListHolder<Duty> addToTheFront(Duty c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelListHolder<Duty> addToTheEnd(Duty c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duty removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duty removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
}
