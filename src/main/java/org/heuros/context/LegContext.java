package org.heuros.context;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.data.ndx.OneDimIndexInt;
import org.heuros.core.entity.context.AbstractEntityContext;
import org.heuros.data.model.Leg;
import org.heuros.data.model.LegView;
import org.heuros.rule.LegRuleContext;

public class LegContext extends AbstractEntityContext<Leg> {

	private static Logger logger = Logger.getLogger(LegContext.class);

	public boolean registerLeg(Leg l) {
		if (((LegRuleContext) this.ruleContext).getIntroducerProxy().introduce(l)) {
			this.dataRepository.addToRepo(l);
			return true;
		}
		return false;
	}

	private OneDimIndexInt<LegView> connectionLegsIndex;

	public OneDimIndexInt<LegView> getConnectionLegsIndex() {
		return this.connectionLegsIndex;
	}

	public void buildLegIndexes(int maxLegConnectionTimeInMins) {
		LegRuleContext legRuleContext = (LegRuleContext) this.ruleContext;
		List<Leg> legs = this.getDataRepository().getModels();

		this.connectionLegsIndex = new OneDimIndexInt<LegView>(new LegView[legs.size()][0]);

		int numOfConnectionsChecked = 0;
		int numOfConnectionsIndexed = 0;
		for (int i = 0; i < legs.size(); i++) {
			Leg pl = legs.get(i);
			for (int j = i + 1; j < legs.size(); j++) {
				Leg nl = legs.get(j);
				if (pl.getArrAirport().getNdx() == nl.getDepAirport().getNdx()) {
					int connTime = (int) ChronoUnit.MINUTES.between(pl.getSibt(), nl.getSobt());
					if (connTime > maxLegConnectionTimeInMins)
						break;
					numOfConnectionsChecked++;
					if (legRuleContext.getConnectionCheckerProxy().areConnectable(pl, nl)) {
						this.connectionLegsIndex.add(pl.getNdx(), nl);
						numOfConnectionsIndexed++;
					}
				}
			}
		}
		LegContext.logger.info(numOfConnectionsChecked + " numbers of leg connections are checked!");
		LegContext.logger.info(numOfConnectionsIndexed + " numbers of leg connections are found!");
		LegContext.logger.info(this.connectionLegsIndex.getNumOfIndexedElements() + " numbers of leg connections are indexed!");
	}
}
