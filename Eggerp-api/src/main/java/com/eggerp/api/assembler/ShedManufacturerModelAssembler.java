package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.ShedManufacturerModel;
import com.eggerp.domain.model.ShedManufacturer;

@Component
public class ShedManufacturerModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ShedManufacturerModel toModel(ShedManufacturer shedManufacturer) {
		return modelMapper.map(shedManufacturer, ShedManufacturerModel.class);
	}
	
	public List<ShedManufacturerModel> toCollectionModel(List<ShedManufacturer> shedManufacturers) {
		return shedManufacturers.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}
	
}