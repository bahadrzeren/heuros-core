package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.repo.RuleRepository;

public class IntroducerProxy<W extends Wrapper<M>, M extends Model> implements Introducer<W, M> {

	private RuleRepository<Introducer<W, M>> repo;

	public IntroducerProxy(RuleRepository<Introducer<W, M>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean introduce(W t) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).introduce(t))
				return false;
		return true;
	}
}
