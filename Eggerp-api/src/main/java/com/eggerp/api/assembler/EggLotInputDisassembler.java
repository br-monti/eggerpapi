package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.EggLotInput;
import com.eggerp.domain.model.EggLot;

@Component
public class EggLotInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EggLot toDomainObject(EggLotInput eggLotInput) {
		return modelMapper.map(eggLotInput, EggLot.class);
	}
	
	public void copyToDomainObject(EggLotInput eggLotInput, EggLot eggLot) {		
		modelMapper.map(eggLotInput, eggLot);
	}
	
}