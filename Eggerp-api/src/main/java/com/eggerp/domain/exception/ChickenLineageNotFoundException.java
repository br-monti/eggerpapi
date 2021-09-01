package com.eggerp.domain.exception;

public class ChickenLineageNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ChickenLineageNotFoundException(String message) {
		super(message);
	}
	
	public ChickenLineageNotFoundException(Long chickenLineageId) {
		this(String.format("Não existe um cadastro de Linhagem com código %d", chickenLineageId));
	}

}
