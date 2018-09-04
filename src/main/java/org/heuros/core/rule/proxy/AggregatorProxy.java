package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.intf.Aggregator;

/**
 * Proxy class for Aggregator rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <P> Type of the parent model instances.
 * @param <C> Type of the child model instances.
 * @see Aggregator
 */
public class AggregatorProxy<P extends Model, C extends View> implements Aggregator<P, C> {

	private Aggregator<P, C> aggregatorImpl = null;

	public AggregatorProxy(Aggregator<P, C> aggregatorImpl) {
		this.aggregatorImpl = aggregatorImpl;
	}

	@Override
	public void append(P p, C c, int hbNdx) {
		this.aggregatorImpl.append(p, c, hbNdx);
	}

	@Override
	public C removeLast(P p, int hbNdx) {
		return this.aggregatorImpl.removeLast(p, hbNdx);
	}

	@Override
	public void reset(P p) {
		this.aggregatorImpl.reset(p);
	}

	@Override
	public void reCalculate(P p, int hbNdx) {
		this.aggregatorImpl.reCalculate(p, hbNdx);
	}

	@Override
	public void softAppend(P p, C c, int hbNdx) {
		this.aggregatorImpl.softAppend(p, c, hbNdx);
	}
}
