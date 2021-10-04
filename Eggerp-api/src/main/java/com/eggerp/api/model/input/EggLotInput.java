package com.eggerp.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eggerp.domain.model.ChickenLot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EggLotInput {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String boxColor;
	
	@Valid
	@Size(min = 1)
	@NotNull
	private List<ChickenLot> chickenLots;
}