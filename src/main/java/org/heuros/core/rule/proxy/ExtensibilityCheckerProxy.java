package org.heuros.core.rule.proxy;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.repo.RuleRepository;

public class ExtensibilityCheckerProxy<M extends Model, 
										E extends Extension, 
										C extends Model, 
										X extends Extension> implements ExtensibilityChecker<M, E, C, X> {

	private RuleRepository<ExtensibilityChecker<M, E, C, X>> repo;

	public ExtensibilityCheckerProxy(RuleRepository<ExtensibilityChecker<M, E, C, X>> repo) {
		this.repo = repo;
	}

	@Override
	public boolean isExtensible(M parentModel, 
								E parentExtension, 
								C childModel, 
								X childExtension) {
		for (int i = 0; i < this.repo.getRules().size(); i++)
			if (!this.repo.getRules().get(i).isExtensible(parentModel, 
															parentExtension, 
															childModel,
															childExtension))
				return false;
		return true;
	}
}
