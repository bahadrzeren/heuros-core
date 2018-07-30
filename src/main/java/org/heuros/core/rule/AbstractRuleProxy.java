package org.heuros.core.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.rule.inf.ConnectionChecker;
import org.heuros.core.rule.inf.ExtensibilityChecker;
import org.heuros.core.rule.inf.Introducer;
import org.heuros.core.rule.inf.ValidationStatus;
import org.heuros.core.rule.inf.Validator;

public class AbstractRuleProxy<T, P> implements RuleRepository<T, P>
												, Introducer<T>
												, ConnectionChecker<T>
												, ExtensibilityChecker<T, P>
												, Validator<T> {

	private Logger logger = Logger.getLogger(AbstractRuleProxy.class);

	protected List<RuleWrapper<Introducer<T>>> introducers = new ArrayList<RuleWrapper<Introducer<T>>>();
	protected List<RuleWrapper<ConnectionChecker<T>>> connectionCheckers = new ArrayList<RuleWrapper<ConnectionChecker<T>>>();
	protected List<RuleWrapper<ExtensibilityChecker<T, P>>> extensibilityCheckers = new ArrayList<RuleWrapper<ExtensibilityChecker<T, P>>>();
	protected List<RuleWrapper<Validator<T>>> validators = new ArrayList<RuleWrapper<Validator<T>>>();

	@Override
	public void registerIntroducer(Introducer<T> introducer) throws Exception {
		if (this.introducers.stream().filter((i) -> i.getRuleImpl() == introducer)
										.findFirst()
										.isPresent())
			logger.error("Introducer is already registered!");
		else
			this.introducers.add(RuleWrapper.getInstance(introducer));
	}

	@Override
	public void registerConnectionChecker(ConnectionChecker<T> connectionChecker) throws Exception {
		if (this.connectionCheckers.stream().filter((i) -> i.getRuleImpl() == connectionChecker)
											.findFirst()
											.isPresent())
			logger.error("Connection checker is already registered!");
		else
			this.connectionCheckers.add(RuleWrapper.getInstance(connectionChecker));
	}

	@Override
	public void registerExtensibilityChecker(ExtensibilityChecker<T, P> extensibilityChecker) throws Exception {
		if (this.extensibilityCheckers.stream().filter((i) -> i.getRuleImpl() == extensibilityChecker)
												.findFirst()
												.isPresent())
			logger.error("Extensibility checker is already registered!");
		else
			this.extensibilityCheckers.add(RuleWrapper.getInstance(extensibilityChecker));
	}

	@Override
	public void registerValidator(Validator<T> validator) throws Exception {
		if (this.validators.stream().filter((i) -> i.getRuleImpl() == validator)
									.findFirst()
									.isPresent())
			logger.error("Validator is already registered!");
		else
			this.validators.add(RuleWrapper.getInstance(validator));
	}

	@Override
	public boolean introduce(T t) {
		for (int i = 0; i < this.introducers.size(); i++)
			if (!this.introducers.get(i).getRuleImpl().introduce(t))
				return false;
		return true;
	}

	@Override
	public boolean areConnectable(T prev, T next) {
		for (int i = 0; i < this.connectionCheckers.size(); i++)
			if (!this.connectionCheckers.get(i).getRuleImpl().areConnectable(prev, next))
				return false;
		return true;
	}

	@Override
	public boolean isExtensible(T item, P parent) {
		for (int i = 0; i < this.extensibilityCheckers.size(); i++)
			if (!this.extensibilityCheckers.get(i).getRuleImpl().isExtensible(item, parent))
				return false;
		return true;
	}

	@Override
	public ValidationStatus isValid(T t) {
		ValidationStatus res = ValidationStatus.valid;
		for (int i = 0; i < this.validators.size(); i++) {
			ValidationStatus valState = this.validators.get(i).getRuleImpl().isValid(t);
			if (valState == ValidationStatus.invalid)
				return ValidationStatus.invalid;
			else
				if (valState == ValidationStatus.extensible)
					res = ValidationStatus.extensible;
		}
		return res;
	}
}
