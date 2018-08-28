package org.heuros.exception;

/**
 * Root class for Heuros specific exceptions.
 * 
 * @author bahadrzeren
 *
 */
public class HeurosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823460289461030945L;

	public HeurosException(String msg) {
		super(msg);
	}
}
