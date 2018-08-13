package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;
import org.heuros.core.rule.inf.Validator;

public class ValidatorRepository<W extends Wrapper<M>, M extends Model> extends AbstractRuleRepository<Validator<W, M>> {
}