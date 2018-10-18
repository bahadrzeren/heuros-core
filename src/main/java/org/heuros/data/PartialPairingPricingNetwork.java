package org.heuros.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.heuros.data.model.DutyView;

public class PartialPairingPricingNetwork {
//	/*
//	 * TODO Single base assumption!!!
//	 */
//	private int hbNdx = 0;

	private Set<Integer> sourceDuties = null;
	private List<HashSet<Integer>> dutyConnections = null;

	public PartialPairingPricingNetwork(int numberOfDuties) {
		this.sourceDuties = new HashSet<Integer>();
		this.dutyConnections = new ArrayList<HashSet<Integer>>(numberOfDuties);
	}

	public void addDuty(DutyView pd, DutyView nd) {
		if (pd == null) {
			this.sourceDuties.add(nd.getNdx());
		} else {
			HashSet<Integer> nextDutyNdxs = this.dutyConnections.get(pd.getNdx());
			if (nextDutyNdxs == null) {
				nextDutyNdxs = new HashSet<Integer>();
				this.dutyConnections.set(pd.getNdx(), nextDutyNdxs);
			}
			nextDutyNdxs.add(nd.getNdx());
		}
	}
}
