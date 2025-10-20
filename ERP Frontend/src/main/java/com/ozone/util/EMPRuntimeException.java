package com.ozone.util;

/**
 * Inventory runtime exception
 * @author Madhuri
 */
public class EMPRuntimeException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -155998367898066022L;

	public EMPRuntimeException () {
		super();
	}
	
	public EMPRuntimeException(String message) {
		super(message);
	}
	
	public EMPRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EMPRuntimeException(Throwable cause) {
		super(cause);
	}
	
	public EMPRuntimeException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
