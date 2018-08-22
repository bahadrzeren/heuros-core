package org.heuros.core.rule.intf;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;

public interface Aggregator<P extends Model, C extends View> extends Rule {
	public void append(P p, C c);
	public C removeLast(P p);
	public void reCalculate(P p);
	public void reset(P p);
}
