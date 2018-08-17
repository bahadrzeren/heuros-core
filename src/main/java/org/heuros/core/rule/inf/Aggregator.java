package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

public interface Aggregator<P extends Model, C extends View> {
	public void append(P m, C c);
	public void removeLast(P m, C c);
	public void reset();
}
