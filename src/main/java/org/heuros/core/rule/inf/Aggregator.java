package org.heuros.core.rule.inf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

public interface Aggregator<P extends Model, C extends View> {
	public void append(P p, C c);
	public C removeLast(P p);
	public void reset(P p);
}
