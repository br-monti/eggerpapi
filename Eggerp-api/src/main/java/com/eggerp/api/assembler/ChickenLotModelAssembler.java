package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.ChickenLotModel;
import com.eggerp.domain.model.ChickenLot;

@Component
public class ChickenLotModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ChickenLotModel toModel(ChickenLot chickenLot) {
		return modelMapper.map(chickenLot, ChickenLotModel.class);
	}
	
	public List<ChickenLotModel> toCollectionModel(List<ChickenLot> chickenLots) {
		return chickenLots.stream()
				.map(chickenLot -> toModel(chickenLot))
				.collect(Collectors.toList());
	}
	
}