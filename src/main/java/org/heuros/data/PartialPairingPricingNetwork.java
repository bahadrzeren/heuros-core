package org.heuros.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.heuros.data.model.Duty;
import org.heuros.data.model.DutyView;

public class PartialPairingPricingNetwork {
//	/*
//	 * TODO Single base assumption!!!
//	 */
//	private int hbNdx = 0;

	private List<Duty> duties = null;

	private Set<Integer> sourceDuties = null;
	private List<HashSet<Integer>> dutyConnections = null;

	public PartialPairingPricingNetwork(List<Duty> duties) {
		this.duties = duties;
		this.sourceDuties = new HashSet<Integer>();
		this.dutyConnections = new ArrayList<HashSet<Integer>>(this.duties.size());
	}

	public void addSourceDuty(DutyView d) {
		this.sourceDuties.add(d.getNdx());
	}

	public void addDuty(DutyView pd, DutyView nd) {
		HashSet<Integer> nextDutyNdxs = this.dutyConnections.get(pd.getNdx());
		if (nextDutyNdxs == null) {
			nextDutyNdxs = new HashSet<Integer>();
			this.dutyConnections.set(pd.getNdx(), nextDutyNdxs);
		}
		nextDutyNdxs.add(nd.getNdx());
	}

	public Set<Integer> getSourceDuties() {
		return this.sourceDuties;
	}

	public List<HashSet<Integer>> getDutyConnections() {
		return this.dutyConnections;
	}

	public Set<Integer> getNextDuties(DutyView pd) {
		return this.dutyConnections.get(pd.getNdx());
	}
}
