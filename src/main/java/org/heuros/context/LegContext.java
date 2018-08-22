package org.heuros.context;

import org.heuros.core.entity.context.AbstractEntityContext;
import org.heuros.data.model.Leg;
import org.heuros.rule.LegRuleContext;

public class LegContext extends AbstractEntityContext<Leg> {
	public boolean registerLeg(Leg l) {
		if (((LegRuleContext) this.ruleContext).getIntroducerProxy().introduce(l)) {
			this.dataRepository.addToRepo(l);
			return true;
		}
		return false;
	}
}
