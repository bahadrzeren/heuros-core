package org.heuros.core.rule.repo;

import org.heuros.core.data.base.Model;
import org.heuros.core.rule.intf.Introducer;

/**
 * Repository class for Introducer rule implementations.
 * 
 * @author bahadrzeren
 *
 * @param <M> Type of the model instances that the stored rule implementations will be applied to.
 * @see Introducer
 */
public class IntroducerRepository<M extends Model>
						extends AbstractRuleRepository<Introducer<M>> {
}