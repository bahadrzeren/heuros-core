package org.heuros.core.entity.context;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.ModelFactory;
import org.heuros.core.entity.context.repo.DataRepository;
import org.heuros.core.rule.RuleContext;

public interface EntityContext<M extends Model> {
	public ModelFactory<M> getModelFactory();
	public DataRepository<M> getDataRepository();
	public RuleContext getRuleContext();
	public EntityContext<M> setModelFactory(ModelFactory<M> modelFactory);
	public EntityContext<M> setDataRepository(DataRepository<M> dataRepository);
	public EntityContext<M> setRuleContext(RuleContext ruleContext);
}
