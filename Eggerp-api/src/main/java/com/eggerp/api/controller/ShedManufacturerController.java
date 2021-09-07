package com.eggerp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eggerp.api.assembler.ShedManufacturerModelAssembler;
import com.eggerp.api.model.ShedManufacturerModel;
import com.eggerp.domain.model.ShedManufacturer;
import com.eggerp.domain.repository.ShedManufacturerRepository;
import com.eggerp.domain.service.ShedManufacturerService;


@RestController
@RequestMapping(value = "/ShedManufacturers")
public class ShedManufacturerController {

	@Autowired
	private ShedManufacturerRepository shedManufacturerRepository;
	
	@Autowired
	private ShedManufacturerService shedManufacturerService;
	
	@Autowired
	private ShedManufacturerModelAssembler shedManufacturerModelAssembler;
		
	@GetMapping
	public Page<ShedManufacturerModel> find(@PageableDefault(size = 10) Pageable pageable) {
		Page<ShedManufacturer> shedManufacturersPage = shedManufacturerRepository.findAll(pageable);
		
		List<ShedManufacturerModel> shedManufacturersModel = shedManufacturerModelAssembler
				.toCollectionModel(shedManufacturersPage.getContent());
		
		Page<ShedManufacturerModel> shedManufacturersModelPage = new PageImpl<>(shedManufacturersModel, pageable, 
				shedManufacturersPage.getTotalElements());
		
		return shedManufacturersModelPage;
	}
	
	@GetMapping("/{shedManufacturerId}")
	public ShedManufacturerModel findById(@PathVariable Long shedManufacturerId) {
		ShedManufacturer shedManufacturer = shedManufacturerService.findById(shedManufacturerId);
		
		return shedManufacturerModelAssembler.toModel(shedManufacturer);
	}
	
	/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShedManufacturerModel create(@RequestBody @Valid ShedManufacturerInput shedManufacturerInput) {
		ShedManufacturer shedManufacturer = shedManufacturerInputDisassembler.toDomainObject(shedManufacturerInput);
		shedManufacturer = shedManufacturerService.save(shedManufacturer);
		
		return shedManufacturerModelAssembler.toModel(shedManufacturer);
	}
	
	@PutMapping("/{shedManufacturerId}")
	public ShedManufacturerModel update(@PathVariable Long shedManufacturerId,
			@RequestBody @Valid ShedManufacturerInput shedManufacturerInput) {
		ShedManufacturer shedManufacturerSaved = shedManufacturerService.findById(shedManufacturerId);
		shedManufacturerInputDisassembler.copyToDomainObject(shedManufacturerInput, shedManufacturerSaved);
		shedManufacturerSaved = shedManufacturerService.save(shedManufacturerSaved);
		
		return shedManufacturerModelAssembler.toModel(shedManufacturerSaved);
	}
	
	@DeleteMapping("/{shedManufacturerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long shedManufacturerId) {
		shedManufacturerService.deleteById(shedManufacturerId);
	}*/
	
}