package com.eggerp.domain.exception;

public class EggLotNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public EggLotNotFoundException(String message) {
		super(message);
	}
	
	public EggLotNotFoundException(Long eggLotId) {
		this(String.format("Não existe um cadastro de Lote de Ovos com código %d", eggLotId));
	}

}
