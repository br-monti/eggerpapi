package com.eggerp.api.model.input;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreationMonitoringInput {
	
	@NotNull
	private int ageWeek;
	
	@NotNull
	private int ageDay;
	
	@NotNull
	private LocalDate dateWeek;
	
	@NotNull
	private int bodyWeight;
	
	@NotNull
	private int food;
	
	@NotNull
	private int water;
	
	@NotNull
	private int discard;
	
	@NotNull
	private int mortality;
	
	@Valid
	@NotNull
	private ChickenLotIdInput chickenLot;
	
}