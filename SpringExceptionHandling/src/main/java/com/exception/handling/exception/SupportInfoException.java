package com.exception.handling.exception;

public class SupportInfoException extends Exception {
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657491283614755649L;

	public SupportInfoException(String msg) {
		super(msg);
	}

	public SupportInfoException(String msg, Throwable t) {
		super(msg, t);
	}
}
