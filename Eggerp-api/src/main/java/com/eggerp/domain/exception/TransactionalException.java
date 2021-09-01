package com.eggerp.domain.exception;

public class TransactionalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransactionalException(String message) {
		super(message);
	}
	
	public TransactionalException(String message, Throwable cause) {
		super(message, cause);
	}
	
}