package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.View;
import org.heuros.core.rule.inf.Aggregator;
import org.heuros.core.rule.repo.RuleRepository;

public class AggregatorProxy<P extends Model, C extends View> implements Aggregator<P, C> {

	private RuleRepository<Aggregator<P, C>> repo;

	public AggregatorProxy(RuleRepository<Aggregator<P, C>> repo) {
		this.repo = repo;
	}

	@Override
	public void append(P p, C c) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			this.repo.getRules().get(i).append(p, c);
	}

	@Override
	public void removeLast(P p, C c) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			this.repo.getRules().get(i).removeLast(p, c);
	}

	@Override
	public void reset() {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			this.repo.getRules().get(i).reset();
	}
}
