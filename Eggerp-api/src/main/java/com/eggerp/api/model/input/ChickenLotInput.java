package com.eggerp.api.model.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChickenLotInput {
	
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	private LocalDate accommodationDate;
	
	@NotNull
	private int initialQuantity;
	
	@NotNull
	private int currentQuantity;
	
	@NotBlank
	private String debicking;
	
	@Valid
	@NotNull
	private ChickenLineageIdInput chickenLineage;
	
	@Valid
	@NotNull
	private ShedIdInput shed;
	
}