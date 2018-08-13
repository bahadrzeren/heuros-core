package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.ConnectionChecker;

public class ConnectionCheckerRepository<W extends Wrapper<M>, M extends Model> extends AbstractRuleRepository<ConnectionChecker<W, M>> {
}