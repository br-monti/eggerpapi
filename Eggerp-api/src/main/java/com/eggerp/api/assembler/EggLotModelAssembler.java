package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.EggLotModel;
import com.eggerp.domain.model.EggLot;

@Component
public class EggLotModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EggLotModel toModel(EggLot eggLot) {
		return modelMapper.map(eggLot, EggLotModel.class);
	}
	
	public List<EggLotModel> toCollectionModel(List<EggLot> eggLots) {
		return eggLots.stream()
				.map(eggLot -> toModel(eggLot))
				.collect(Collectors.toList());
	}
	
}