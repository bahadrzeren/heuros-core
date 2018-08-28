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
	public void append(P p, C c) {
		this.aggregatorImpl.append(p, c);
	}

	@Override
	public C removeLast(P p) {
		return this.aggregatorImpl.removeLast(p);
	}

	@Override
	public void reset(P p) {
		this.aggregatorImpl.reset(p);
	}

	@Override
	public void reCalculate(P p) {
		this.aggregatorImpl.reset(p);
	}

	@Override
	public void softAppend(P p, C c) {
		this.aggregatorImpl.softAppend(p, c);
	}
}
