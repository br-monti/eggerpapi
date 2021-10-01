package com.eggerp.domain.exception;

public class ProductionMonitoringNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ProductionMonitoringNotFoundException(String message) {
		super(message);
	}
	
	public ProductionMonitoringNotFoundException(Long productionMonitoringId) {
		this(String.format("Não existe um cadastro de Monitoramento de Produção com código %d", productionMonitoringId));
	}

}
