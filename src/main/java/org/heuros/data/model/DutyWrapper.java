package org.heuros.data.model;

import org.heuros.core.data.base.AbstractWrapper;
import org.heuros.core.data.base.ModelListHolder;
import org.heuros.core.rule.RuleContext;

public class DutyWrapper extends AbstractWrapper<DutyModel, DutyExtension>
										implements ModelListHolder<LegModel> {

	public DutyWrapper(RuleContext<DutyModel, DutyExtension> dutyRuleContext, 
						DutyModel wrappee,
						DutyExtension extension) {
		super(dutyRuleContext, wrappee, extension);
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
