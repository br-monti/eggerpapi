package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.ChickenLineageModel;
import com.eggerp.domain.model.ChickenLineage;

@Component
public class ChickenLineageModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ChickenLineageModel toModel(ChickenLineage chickenLineage) {
		return modelMapper.map(chickenLineage, ChickenLineageModel.class);
	}
	
	public List<ChickenLineageModel> toCollectionModel(List<ChickenLineage> chickenLineages) {
		return chickenLineages.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}
	
}