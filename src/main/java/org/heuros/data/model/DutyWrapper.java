package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;

public class DutyWrapper extends AbstractWrapper<DutyModel, DutyExtension>
										implements ModelListHolder<LegModel> {

	public DutyWrapper(//	DutyRuleContext dutyRuleContext, 
						DutyModel wrappee) {
		super(//	dutyRuleContext, 
				wrappee);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelListHolder<LegModel> addToTheFront(LegModel c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelListHolder<LegModel> addToTheEnd(LegModel c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LegModel removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LegModel removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
}
