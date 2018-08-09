package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.repo.RuleRepository;

public class IntroducerProxy<M extends Model> implements Introducer<M> {

	private RuleRepository<Introducer<M>, M> repo;

	public IntroducerProxy(RuleRepository<Introducer<M>, M> repo) {
		this.repo = repo;
	}

	@Override
	public boolean introduce(M t) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).introduce(t))
				return false;
		return true;
	}
}
