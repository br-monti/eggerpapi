package com.eggerp.domain.exception;

public class ShedNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ShedNotFoundException(String message) {
		super(message);
	}
	
	public ShedNotFoundException(Long shedId) {
		this(String.format("Não existe um cadastro de Aviário com código %d", shedId));
	}

}
