package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.ProductionMonitoringInput;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.ProductionMonitoring;

@Component
public class ProductionMonitoringInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductionMonitoring toDomainObject(ProductionMonitoringInput productionMonitoringInput) {
		return modelMapper.map(productionMonitoringInput, ProductionMonitoring.class);
	}
	
	public void copyToDomainObject(ProductionMonitoringInput productionMonitoringInput, ProductionMonitoring productionMonitoring) {
		
		productionMonitoring.setChickenLot(new ChickenLot());
		modelMapper.map(productionMonitoringInput, productionMonitoring);
	}
	
}