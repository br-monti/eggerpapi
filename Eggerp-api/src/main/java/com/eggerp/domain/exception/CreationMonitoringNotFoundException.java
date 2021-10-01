package com.eggerp.domain.exception;

public class CreationMonitoringNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public CreationMonitoringNotFoundException(String message) {
		super(message);
	}
	
	public CreationMonitoringNotFoundException(Long creationMonitoringId) {
		this(String.format("Não existe um cadastro de Monitoramento de Criação com código %d", creationMonitoringId));
	}

}
