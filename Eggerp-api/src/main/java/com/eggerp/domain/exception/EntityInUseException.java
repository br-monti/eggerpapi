package com.eggerp.domain.exception;

public class EntityInUseException extends TransactionalException {

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String message) {
		super(message);
	}
	
}
