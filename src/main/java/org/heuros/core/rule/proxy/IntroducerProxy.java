package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.repo.RuleRepository;

public class IntroducerProxy<M extends Model, E extends Extension> implements Introducer<M, E> {

	private RuleRepository<Introducer<M, E>> repo;

	public IntroducerProxy(RuleRepository<Introducer<M, E>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean introduce(M m, E e) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).introduce(m, e))
				return false;
		return true;
	}
}
