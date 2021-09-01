package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.ChickenLineageInput;
import com.eggerp.domain.model.ChickenLineage;

@Component
public class ChickenLineageInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ChickenLineage toDomainObject(ChickenLineageInput chickenLineageInput) {
		return modelMapper.map(chickenLineageInput, ChickenLineage.class);
	}
	
	public void copyToDomainObject(ChickenLineageInput chickenLineageInput, ChickenLineage chickenLineage) {
		modelMapper.map(chickenLineageInput, chickenLineage);
	}
	
}