package com.eggerp.domain.exception;

public class ShedManufacturerNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ShedManufacturerNotFoundException(String message) {
		super(message);
	}
	
	public ShedManufacturerNotFoundException(Long shedManufacturerId) {
		this(String.format("Não existe um cadastro de Fabricante de Aviário com código %d", shedManufacturerId));
	}

}
