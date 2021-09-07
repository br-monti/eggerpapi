package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.ShedModel;
import com.eggerp.domain.model.Shed;

@Component
public class ShedModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ShedModel toModel(Shed shed) {
		return modelMapper.map(shed, ShedModel.class);
	}
	
	public List<ShedModel> toCollectionModel(List<Shed> sheds) {
		return sheds.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}
	
}