package com.eggerp.domain.exception;

public class ChickenLotNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ChickenLotNotFoundException(String message) {
		super(message);
	}
	
	public ChickenLotNotFoundException(Long chickenLotId) {
		this(String.format("Não existe um cadastro de Lote de Aves com código %d", chickenLotId));
	}

}
