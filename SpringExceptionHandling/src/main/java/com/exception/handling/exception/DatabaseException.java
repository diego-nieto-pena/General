package com.exception.handling.exception;

public class DatabaseException extends Exception {
	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657491283614455649L;

	public DatabaseException(String msg) {
		super(msg);
	}

	public DatabaseException(String msg, Throwable t) {
		super(msg, t);
	}
}
