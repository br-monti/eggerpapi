package com.eggerp.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedInput {
	
	@JsonIgnore
	private Long id;

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