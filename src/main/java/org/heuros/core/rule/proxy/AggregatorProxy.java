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
	public void appendFw(P p, C c) {
		this.aggregatorImpl.appendFw(p, c);
	}

	@Override
	public void appendBw(P p, C c) {
		this.aggregatorImpl.appendBw(p, c);
	}

	@Override
	public C removeLast(P p) {
		return this.aggregatorImpl.removeLast(p);
	}

	@Override
	public C removeFirst(P p) {
		return this.aggregatorImpl.removeFirst(p);
	}

	@Override
	public void removeAll(P p) {
		this.aggregatorImpl.removeAll(p);
	}

	@Override
	public void reset(P p) {
		this.aggregatorImpl.reset(p);
	}

	@Override
	public boolean reCalculate(P p) {
		return this.aggregatorImpl.reCalculate(p);
	}

	@Override
	public void softAppendFw(P p, C c) {
		this.aggregatorImpl.softAppendFw(p, c);
	}
	@Override
	public void softAppendBw(P p, C c) {
		this.aggregatorImpl.softAppendBw(p, c);
	}
}
