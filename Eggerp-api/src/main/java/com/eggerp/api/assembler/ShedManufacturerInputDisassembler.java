package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.ShedManufacturerInput;
import com.eggerp.domain.model.ShedManufacturer;

@Component
public class ShedManufacturerInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ShedManufacturer toDomainObject(ShedManufacturerInput shedManufacturerInput) {
		return modelMapper.map(shedManufacturerInput, ShedManufacturer.class);
	}
	
	public void copyToDomainObject(ShedManufacturerInput shedManufacturerInput, ShedManufacturer shedManufacturer) {
		modelMapper.map(shedManufacturerInput, shedManufacturer);
	}
	
}