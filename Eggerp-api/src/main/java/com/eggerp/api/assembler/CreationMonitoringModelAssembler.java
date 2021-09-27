package com.eggerp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eggerp.api.model.CreationMonitoringModel;
import com.eggerp.domain.model.CreationMonitoring;

@Component
public class CreationMonitoringModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CreationMonitoringModel toModel(CreationMonitoring creationMonitoring) {
		return modelMapper.map(creationMonitoring, CreationMonitoringModel.class);
	}
	
	public List<CreationMonitoringModel> toCollectionModel(List<CreationMonitoring> creationMonitorings) {
		return creationMonitorings.stream()
				.map(creationMonitoring -> toModel(creationMonitoring))
				.collect(Collectors.toList());
	}
	
}