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

import com.eggerp.api.assembler.ChickenLineageInputDisassembler;
import com.eggerp.api.assembler.ChickenLineageModelAssembler;
import com.eggerp.api.model.ChickenLineageModel;
import com.eggerp.api.model.input.ChickenLineageInput;
import com.eggerp.domain.filter.ChickenLineageFilter;
import com.eggerp.domain.model.ChickenLineage;
import com.eggerp.domain.repository.ChickenLineageRepository;
import com.eggerp.domain.service.ChickenLineageService;
import com.eggerp.infrastucture.repository.spec.ChickenLineageSpecs;

@RestController
@RequestMapping(value = "/ChickenLineages")
public class ChickenLineageController {

	@Autowired
	private ChickenLineageRepository chickenLineageRepository;
	
	@Autowired
	private ChickenLineageService chickenLineageService;
	
	@Autowired
	private ChickenLineageModelAssembler chickenLineageModelAssembler;
	
	@Autowired
	private ChickenLineageInputDisassembler chickenLineageInputDisassembler;
	
	@GetMapping
	public Page<ChickenLineageModel> find(ChickenLineageFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<ChickenLineage> chickenLineagesPage = chickenLineageRepository.findAll(ChickenLineageSpecs.filter(filter), pageable);
		
		List<ChickenLineageModel> chickenLineagesModel = chickenLineageModelAssembler
				.toCollectionModel(chickenLineagesPage.getContent());
		
		Page<ChickenLineageModel> chickenLineagesModelPage = new PageImpl<>(chickenLineagesModel, pageable, 
				chickenLineagesPage.getTotalElements());
		
		return chickenLineagesModelPage;
	}
	
	@GetMapping("/{chickenLineageId}")
	public ChickenLineageModel findById(@PathVariable Long chickenLineageId) {
		ChickenLineage chickenLineage = chickenLineageService.findById(chickenLineageId);
		
		return chickenLineageModelAssembler.toModel(chickenLineage);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ChickenLineageModel create(@RequestBody @Valid ChickenLineageInput chickenLineageInput) {
		ChickenLineage chickenLineage = chickenLineageInputDisassembler.toDomainObject(chickenLineageInput);
		chickenLineage = chickenLineageService.save(chickenLineage);
		
		return chickenLineageModelAssembler.toModel(chickenLineage);
	}
	
	@PutMapping("/{chickenLineageId}")
	public ChickenLineageModel update(@PathVariable Long chickenLineageId,
			@RequestBody @Valid ChickenLineageInput chickenLineageInput) {
		ChickenLineage chickenLineageSaved = chickenLineageService.findById(chickenLineageId);
		chickenLineageInputDisassembler.copyToDomainObject(chickenLineageInput, chickenLineageSaved);
		chickenLineageSaved = chickenLineageService.save(chickenLineageSaved);
		
		return chickenLineageModelAssembler.toModel(chickenLineageSaved);
	}
	
	@DeleteMapping("/{chickenLineageId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long chickenLineageId) {
		chickenLineageService.deleteById(chickenLineageId);
	}
	
}