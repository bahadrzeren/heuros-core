package org.heuros.exception;

/**
 * Exception thrown when rule implementation does not has RuleImplementation annotation.
 * 
 * @author bahadrzeren
 *
 */
public class RuleAnnotationIsMissing extends HeurosException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 332710179724504105L;

	public RuleAnnotationIsMissing(String msg) {
		super(msg);
	}
}
