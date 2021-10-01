package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.ProductionMonitoringModel;
import com.eggerp.domain.model.ProductionMonitoring;

@Component
public class ProductionMonitoringModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductionMonitoringModel toModel(ProductionMonitoring productionMonitoring) {
		return modelMapper.map(productionMonitoring, ProductionMonitoringModel.class);
	}
	
	public List<ProductionMonitoringModel> toCollectionModel(List<ProductionMonitoring> productionMonitorings) {
		return productionMonitorings.stream()
				.map(productionMonitoring -> toModel(productionMonitoring))
				.collect(Collectors.toList());
	}
	
}