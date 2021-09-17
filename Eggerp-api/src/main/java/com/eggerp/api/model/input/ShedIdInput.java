package com.eggerp.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedIdInput {

	@NotNull
	private Long id;
	
}