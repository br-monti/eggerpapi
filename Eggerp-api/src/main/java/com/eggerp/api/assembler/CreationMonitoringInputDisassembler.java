package com.eggerp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.input.CreationMonitoringInput;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.CreationMonitoring;

@Component
public class CreationMonitoringInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CreationMonitoring toDomainObject(CreationMonitoringInput creationMonitoringInput) {
		return modelMapper.map(creationMonitoringInput, CreationMonitoring.class);
	}
	
	public void copyToDomainObject(CreationMonitoringInput creationMonitoringInput, CreationMonitoring creationMonitoring) {
		
		creationMonitoring.setChickenLot(new ChickenLot());
		modelMapper.map(creationMonitoringInput, creationMonitoring);
	}
	
}