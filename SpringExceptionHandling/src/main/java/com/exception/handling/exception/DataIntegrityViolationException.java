package com.exception.handling.exception;

public class DataIntegrityViolationException extends DataAccessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3719703018925265404L;

	public DataIntegrityViolationException(String msg) {
		super(msg);
	}

	public DataIntegrityViolationException(String msg, Throwable t) {
		super(msg, t);
	}
}
