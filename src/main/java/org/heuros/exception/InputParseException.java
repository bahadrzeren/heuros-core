package org.heuros.exception;

/**
 * Exception thrown when input file/line could not be parsed.
 * 
 * @author bahadrzeren
 *
 */
public class InputParseException extends HeurosException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1307728028720929877L;

	public InputParseException(String msg) {
		super(msg);
	}
}
