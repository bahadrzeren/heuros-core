package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.intf.Introducer;
import org.heuros.core.rule.repo.RuleRepository;

/**
 * Proxy class for Introducer rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that will be initialized.
 * @see Introducer
 */
public class IntroducerProxy<M extends Model> implements Introducer<M> {

	private RuleRepository<Introducer<M>> repo = null;

	public IntroducerProxy(RuleRepository<Introducer<M>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean introduce(M m) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).introduce(m))
				return false;
		return true;
	}
}
