package com.eggerp.api.model.input;

import javax.validation.constraints.NotNull;

import com.eggerp.domain.model.ShedManufacturer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedIdInput {

	@NotNull
	private Long id;
	
	@JsonIgnore
	private ShedManufacturer shedManufacturer;
	
}