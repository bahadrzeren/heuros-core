package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;
import org.heuros.core.rule.RuleContext;

public class PairWrapper extends AbstractWrapper<PairModel, PairExtension>
										implements ModelListHolder<DutyModel> {

	public PairWrapper(RuleContext<PairModel, PairExtension> pairRuleContext, 
						PairModel wrappee,
						PairExtension extension) {
		super(pairRuleContext, wrappee, extension);
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
