package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.rule.inf.Validator;

public class ValidatorRepository<M extends Model, E extends Extension>
						extends AbstractRuleRepository<Validator<M, E>> {
}
