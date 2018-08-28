package org.heuros.exception;

/**
 * Exception thrown when rule implementation could not be fully registered by all the rule repositories.
 * 
 * @author bahadrzeren
 *
 */
public class RuleRegistrationMatchingException extends HeurosException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252180649946334989L;

	public RuleRegistrationMatchingException(String msg) {
		super(msg);
	}
}
