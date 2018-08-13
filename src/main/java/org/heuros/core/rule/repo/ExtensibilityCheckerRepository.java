package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.ExtensibilityChecker;

public class ExtensibilityCheckerRepository<M extends Model, 
											E extends Extension, 
											C extends Model, 
											X extends Extension>
				extends AbstractRuleRepository<ExtensibilityChecker<M, E, C, X>> {
}