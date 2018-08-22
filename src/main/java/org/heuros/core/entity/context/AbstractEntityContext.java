package org.heuros.core.entity.context;

import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.ModelFactory;
import org.heuros.core.entity.context.repo.DataRepository;
import org.heuros.core.rule.RuleContext;

public class AbstractEntityContext<M extends Model> implements EntityContext<M> {

	protected ModelFactory<M> modelFactory;
	protected DataRepository<M> dataRepository;
	protected RuleContext ruleContext;

	@Override
	public ModelFactory<M> getModelFactory() {
		return this.modelFactory;
	}

	@Override
	public DataRepository<M> getDataRepository() {
		return this.dataRepository;
	}

	@Override
	public RuleContext getRuleContext() {
		return this.ruleContext;
	}

	@Override
	public EntityContext<M> setModelFactory(ModelFactory<M> modelFactory) {
		this.modelFactory = modelFactory;
		return this;
	}

	@Override
	public EntityContext<M> setDataRepository(DataRepository<M> dataRepository) {
		this.dataRepository = dataRepository;
		return this;
	}

	@Override
	public EntityContext<M> setRuleContext(RuleContext ruleContext) {
		this.ruleContext = ruleContext;
		return this;
	}
}
