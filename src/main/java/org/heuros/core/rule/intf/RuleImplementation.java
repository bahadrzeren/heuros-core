package org.heuros.core.rule.intf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation class used to flag rule implementations and provide some metadata.
 * 
 * @author bahadrzeren
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface RuleImplementation {
	public String ruleName();
	public String description();
	public String violationMessage();
}
