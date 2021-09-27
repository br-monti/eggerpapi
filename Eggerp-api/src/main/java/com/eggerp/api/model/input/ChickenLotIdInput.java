package com.eggerp.api.model.input;

import javax.validation.constraints.NotNull;

import com.eggerp.domain.model.ChickenLineage;
import com.eggerp.domain.model.Shed;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChickenLotIdInput {

	@NotNull
	private Long id;
	
	@JsonIgnore
	private ChickenLineage chickenLineage;
	
	@JsonIgnore
	private Shed shed;
	
}