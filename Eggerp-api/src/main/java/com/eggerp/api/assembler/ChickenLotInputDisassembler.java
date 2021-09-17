package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.ChickenLotInput;
import com.eggerp.domain.model.ChickenLineage;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.Shed;

@Component
public class ChickenLotInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ChickenLot toDomainObject(ChickenLotInput chickenLotInput) {
		return modelMapper.map(chickenLotInput, ChickenLot.class);
	}
	
	public void copyToDomainObject(ChickenLotInput chickenLotInput, ChickenLot chickenLot) {
		
		chickenLot.setChickenLineage(new ChickenLineage());
		chickenLot.setShed(new Shed());
		modelMapper.map(chickenLotInput, chickenLot);
	}
	
}