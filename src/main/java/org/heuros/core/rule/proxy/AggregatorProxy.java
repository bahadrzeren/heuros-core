package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.Aggregator;

public class AggregatorProxy<P extends Model, C extends View> implements Aggregator<P, C> {

	private Aggregator<P, C> aggregatorImpl;

	public AggregatorProxy(Aggregator<P, C> aggregatorImpl) {
		this.aggregatorImpl = aggregatorImpl;
	}

	@Override
	public void append(P p, C c) {
		this.aggregatorImpl.append(p, c);
	}

	@Override
	public C removeLast(P p) {
		return this.aggregatorImpl.removeLast(p);
	}

	@Override
	public void reset() {
		this.aggregatorImpl.reset();
	}
}
