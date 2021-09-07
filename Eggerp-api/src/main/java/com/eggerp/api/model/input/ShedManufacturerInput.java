package com.eggerp.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedManufacturerInput {

	@NotBlank
	private String manufacturer;
	
}