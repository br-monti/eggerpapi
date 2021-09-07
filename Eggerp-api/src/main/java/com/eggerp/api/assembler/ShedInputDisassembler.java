package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.ShedInput;
import com.eggerp.domain.model.Shed;

@Component
public class ShedInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Shed toDomainObject(ShedInput shedInput) {
		return modelMapper.map(shedInput, Shed.class);
	}
	
	public void copyToDomainObject(ShedInput shedInput, Shed shed) {
		modelMapper.map(shedInput, shed);
	}
	
}