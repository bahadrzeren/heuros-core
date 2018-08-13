package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ExtensibilityChecker;

public class ExtensibilityCheckerRepository<W extends Wrapper<M>, M extends Model, R extends Wrapper<C>, C extends Model>
				extends AbstractRuleRepository<ExtensibilityChecker<W, M, R, C>> {
}