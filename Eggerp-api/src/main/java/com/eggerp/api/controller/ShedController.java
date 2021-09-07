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

import com.eggerp.api.assembler.ShedInputDisassembler;
import com.eggerp.api.assembler.ShedModelAssembler;
import com.eggerp.api.model.ShedModel;
import com.eggerp.api.model.input.ShedInput;
import com.eggerp.domain.filter.ShedFilter;
import com.eggerp.domain.model.Shed;
import com.eggerp.domain.repository.ShedRepository;
import com.eggerp.domain.service.ShedService;
import com.eggerp.infrastucture.repository.spec.ShedSpecs;

@RestController
@RequestMapping(value = "/Sheds")
public class ShedController {

	@Autowired
	private ShedRepository shedRepository;
	
	@Autowired
	private ShedService shedService;
	
	@Autowired
	private ShedModelAssembler shedModelAssembler;
	
	@Autowired
	private ShedInputDisassembler shedInputDisassembler;
	
	@GetMapping
	public Page<ShedModel> find(ShedFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<Shed> shedsPage = shedRepository.findAll(ShedSpecs.filter(filter), pageable);
		
		List<ShedModel> shedsModel = shedModelAssembler
				.toCollectionModel(shedsPage.getContent());
		
		Page<ShedModel> shedsModelPage = new PageImpl<>(shedsModel, pageable, 
				shedsPage.getTotalElements());
		
		return shedsModelPage;
	}
	
	@GetMapping("/{shedId}")
	public ShedModel findById(@PathVariable Long shedId) {
		Shed shed = shedService.findById(shedId);
		
		return shedModelAssembler.toModel(shed);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShedModel create(@RequestBody @Valid ShedInput shedInput) {
		Shed shed = shedInputDisassembler.toDomainObject(shedInput);
		shed = shedService.save(shed);
		
		return shedModelAssembler.toModel(shed);
	}
	
	@PutMapping("/{shedId}")
	public ShedModel update(@PathVariable Long shedId,
			@RequestBody @Valid ShedInput shedInput) {
		Shed shedSaved = shedService.findById(shedId);
		shedInputDisassembler.copyToDomainObject(shedInput, shedSaved);
		shedSaved = shedService.save(shedSaved);
		
		return shedModelAssembler.toModel(shedSaved);
	}
	
	@DeleteMapping("/{shedId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long shedId) {
		shedService.deleteById(shedId);
	}
	
}