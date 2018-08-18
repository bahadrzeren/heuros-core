package org.heuros.data.model;

import org.heuros.core.data.base.View;

public interface DutyLegView extends View {
	public DutyView getDuty();
	public LegView getLeg();
	public boolean isActive();
}
