package org.heuros.api.model;

import java.util.List;

import org.heuros.api.base.AModel;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Duty extends AModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private List<DutyLeg> dutyLegs;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Duty(){
		super();
	}

	public List<DutyLeg> getDutyLegs() {
		return dutyLegs;
	}

	public void setDutyLegs(List<DutyLeg> dutyLegs) {
		this.dutyLegs = dutyLegs;
	}
}
