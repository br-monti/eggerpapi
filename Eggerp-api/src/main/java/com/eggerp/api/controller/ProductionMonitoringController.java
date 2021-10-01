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

import com.eggerp.api.assembler.ProductionMonitoringInputDisassembler;
import com.eggerp.api.assembler.ProductionMonitoringModelAssembler;
import com.eggerp.api.model.ProductionMonitoringModel;
import com.eggerp.api.model.input.ProductionMonitoringInput;
import com.eggerp.domain.exception.ProductionMonitoringNotFoundException;
import com.eggerp.domain.exception.TransactionalException;
import com.eggerp.domain.filter.ProductionMonitoringFilter;
import com.eggerp.domain.model.ProductionMonitoring;
import com.eggerp.domain.repository.ProductionMonitoringRepository;
import com.eggerp.domain.service.ProductionMonitoringService;
import com.eggerp.infrastucture.repository.spec.ProductionMonitoringSpecs;

@RestController
@RequestMapping(value = "/ProductionMonitorings")
public class ProductionMonitoringController {

	@Autowired
	private ProductionMonitoringRepository productionMonitoringRepository;
	
	@Autowired
	private ProductionMonitoringService productionMonitoringService;
	
	@Autowired
	private ProductionMonitoringModelAssembler productionMonitoringModelAssembler;
	
	@Autowired
	private ProductionMonitoringInputDisassembler productionMonitoringInputDisassembler;
	
	@GetMapping
	public Page<ProductionMonitoringModel> find(ProductionMonitoringFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<ProductionMonitoring> productionMonitoringsPage = productionMonitoringRepository.findAll(ProductionMonitoringSpecs.filter(filter), pageable);
		
		List<ProductionMonitoringModel> productionMonitoringsModel = productionMonitoringModelAssembler
				.toCollectionModel(productionMonitoringsPage.getContent());
		
		Page<ProductionMonitoringModel> productionMonitoringsModelPage = new PageImpl<>(productionMonitoringsModel, pageable, 
				productionMonitoringsPage.getTotalElements());
		
		return productionMonitoringsModelPage;
	}
	
	@GetMapping("/{productionMonitoringId}")
	public ProductionMonitoringModel findById(@PathVariable Long productionMonitoringId) {
		ProductionMonitoring productionMonitoring = productionMonitoringService.findById(productionMonitoringId);
		
		return productionMonitoringModelAssembler.toModel(productionMonitoring);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductionMonitoringModel create(@RequestBody @Valid ProductionMonitoringInput productionMonitoringInput) {
		try {
			ProductionMonitoring productionMonitoring = productionMonitoringInputDisassembler.toDomainObject(productionMonitoringInput);
			productionMonitoring = productionMonitoringService.save(productionMonitoring);
		
			return productionMonitoringModelAssembler.toModel(productionMonitoring);
		} catch (ProductionMonitoringNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@PutMapping("/{productionMonitoringId}")
	public ProductionMonitoringModel update(@PathVariable Long productionMonitoringId,
			@RequestBody @Valid ProductionMonitoringInput productionMonitoringInput) {
		
		try {
			ProductionMonitoring productionMonitoringSaved = productionMonitoringService.findById(productionMonitoringId);
			productionMonitoringInputDisassembler.copyToDomainObject(productionMonitoringInput, productionMonitoringSaved);
			productionMonitoringSaved = productionMonitoringService.save(productionMonitoringSaved);
		
			return productionMonitoringModelAssembler.toModel(productionMonitoringSaved);
		} catch (ProductionMonitoringNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@DeleteMapping("/{productionMonitoringId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long productionMonitoringId) {
		productionMonitoringService.deleteById(productionMonitoringId);
	}
	
}