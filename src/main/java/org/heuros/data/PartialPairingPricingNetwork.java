package org.heuros.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
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

	private int[] sourceDutyArray = null;
	private int[][] dutyConnectionArray = null;
	

	public PartialPairingPricingNetwork(List<Duty> duties) {
		this.duties = duties;
		this.sourceDuties = new HashSet<Integer>();
		this.dutyConnections = new ArrayList<HashSet<Integer>>(this.duties.size());
		this.sourceDutyArray = new int[0];
		this.dutyConnectionArray = new int[this.duties.size()][0];
	}

	public void addSourceDuty(DutyView d) {
		if (this.sourceDuties.add(d.getNdx()))
			this.sourceDutyArray = ArrayUtils.add(this.sourceDutyArray, d.getNdx());
	}

	public void addDuty(DutyView pd, DutyView nd) {
		HashSet<Integer> nextDutyNdxs = this.dutyConnections.get(pd.getNdx());
		if (nextDutyNdxs == null) {
			nextDutyNdxs = new HashSet<Integer>();
			this.dutyConnections.set(pd.getNdx(), nextDutyNdxs);
		}
		if (nextDutyNdxs.add(nd.getNdx()))
			dutyConnectionArray[pd.getNdx()] = ArrayUtils.add(dutyConnectionArray[pd.getNdx()], nd.getNdx());
	}

//	public Set<Integer> getSourceDuties() {
//		return this.sourceDuties;
//	}
//
//	public List<HashSet<Integer>> getDutyConnections() {
//		return this.dutyConnections;
//	}
//
//	public Set<Integer> getNextDuties(DutyView pd) {
//		return this.dutyConnections.get(pd.getNdx());
//	}

	public int[] getSourceDuties() {
		return this.sourceDutyArray;
	}

	public int[][] getDutyConnections() {
		return this.dutyConnectionArray;
	}

	public int[] getNextDuties(DutyView pd) {
		return this.dutyConnectionArray[pd.getNdx()];
	}
}
