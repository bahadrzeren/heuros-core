package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;
import org.heuros.rule.PairRuleContext;

public class PairWrapper extends AbstractWrapper<PairModel, PairExtension>
										implements ModelListHolder<DutyModel> {

	public PairWrapper(PairRuleContext pairRuleContext, PairModel wrappee) {
		super(pairRuleContext, wrappee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelListHolder<DutyModel> addToTheFront(DutyModel c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelListHolder<DutyModel> addToTheEnd(DutyModel c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DutyModel removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DutyModel removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
}
