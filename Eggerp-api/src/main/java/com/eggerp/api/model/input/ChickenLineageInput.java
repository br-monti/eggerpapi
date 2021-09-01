package com.eggerp.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChickenLineageInput {

	@NotBlank
	private String lineage;
	
	@NotBlank
	private String chickenColor;
	
	@NotBlank
	private String provider;
	
}