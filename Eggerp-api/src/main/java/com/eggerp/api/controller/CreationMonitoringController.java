package com.eggerp.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eggerp.api.assembler.CreationMonitoringInputDisassembler;
import com.eggerp.api.assembler.CreationMonitoringModelAssembler;
import com.eggerp.api.model.CreationMonitoringModel;
import com.eggerp.api.model.input.CreationMonitoringInput;
import com.eggerp.domain.exception.CreationMonitoringNotFoundException;
import com.eggerp.domain.exception.TransactionalException;
import com.eggerp.domain.filter.CreationMonitoringFilter;
import com.eggerp.domain.model.CreationMonitoring;
import com.eggerp.domain.repository.CreationMonitoringRepository;
import com.eggerp.domain.service.CreationMonitoringService;
import com.eggerp.infrastucture.repository.spec.CreationMonitoringSpecs;

@RestController
@RequestMapping(value = "/CreationMonitorings")
public class CreationMonitoringController {

	@Autowired
	private CreationMonitoringRepository creationMonitoringRepository;
	
	@Autowired
	private CreationMonitoringService creationMonitoringService;
	
	@Autowired
	private CreationMonitoringModelAssembler creationMonitoringModelAssembler;
	
	@Autowired
	private CreationMonitoringInputDisassembler creationMonitoringInputDisassembler;
	
	@GetMapping
	public Page<CreationMonitoringModel> find(CreationMonitoringFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<CreationMonitoring> creationMonitoringsPage = creationMonitoringRepository.findAll(CreationMonitoringSpecs.filter(filter), pageable);
		
		List<CreationMonitoringModel> creationMonitoringsModel = creationMonitoringModelAssembler
				.toCollectionModel(creationMonitoringsPage.getContent());
		
		Page<CreationMonitoringModel> creationMonitoringsModelPage = new PageImpl<>(creationMonitoringsModel, pageable, 
				creationMonitoringsPage.getTotalElements());
		
		return creationMonitoringsModelPage;
	}
	
	@GetMapping("/{creationMonitoringId}")
	public CreationMonitoringModel findById(@PathVariable Long creationMonitoringId) {
		CreationMonitoring creationMonitoring = creationMonitoringService.findById(creationMonitoringId);
		
		return creationMonitoringModelAssembler.toModel(creationMonitoring);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreationMonitoringModel create(@RequestBody @Valid CreationMonitoringInput creationMonitoringInput) {
		try {
			CreationMonitoring creationMonitoring = creationMonitoringInputDisassembler.toDomainObject(creationMonitoringInput);
			creationMonitoring = creationMonitoringService.save(creationMonitoring);
		
			return creationMonitoringModelAssembler.toModel(creationMonitoring);
		} catch (CreationMonitoringNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@PutMapping("/{creationMonitoringId}")
	public CreationMonitoringModel update(@PathVariable Long creationMonitoringId,
			@RequestBody @Valid CreationMonitoringInput creationMonitoringInput) {
		
		try {
			CreationMonitoring creationMonitoringSaved = creationMonitoringService.findById(creationMonitoringId);
			creationMonitoringInputDisassembler.copyToDomainObject(creationMonitoringInput, creationMonitoringSaved);
			creationMonitoringSaved = creationMonitoringService.save(creationMonitoringSaved);
		
			return creationMonitoringModelAssembler.toModel(creationMonitoringSaved);
		} catch (CreationMonitoringNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@DeleteMapping("/{creationMonitoringId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long creationMonitoringId) {
		creationMonitoringService.deleteById(creationMonitoringId);
	}
	
}