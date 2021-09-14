package com.eggerp.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedInput {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String type;
	
	@NotNull
	private int capacity;
	
	@NotBlank
	private String model;
	
	@Valid
	@NotNull
	private ShedManufacturerIdInput shedManufacturer;
	
}