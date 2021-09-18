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

import com.eggerp.api.assembler.ChickenLotInputDisassembler;
import com.eggerp.api.assembler.ChickenLotModelAssembler;
import com.eggerp.api.model.ChickenLotModel;
import com.eggerp.api.model.input.ChickenLotInput;
import com.eggerp.domain.exception.ChickenLotNotFoundException;
import com.eggerp.domain.exception.TransactionalException;
import com.eggerp.domain.filter.ChickenLotFilter;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.repository.ChickenLotRepository;
import com.eggerp.domain.service.ChickenLotService;
import com.eggerp.infrastucture.repository.spec.ChickenLotSpecs;

@RestController
@RequestMapping(value = "/ChickenLots")
public class ChickenLotController {

	@Autowired
	private ChickenLotRepository chickenLotRepository;
	
	@Autowired
	private ChickenLotService chickenLotService;
	
	@Autowired
	private ChickenLotModelAssembler chickenLotModelAssembler;
	
	@Autowired
	private ChickenLotInputDisassembler chickenLotInputDisassembler;
	
	@GetMapping
	public Page<ChickenLotModel> find(ChickenLotFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<ChickenLot> chickenLotsPage = chickenLotRepository.findAll(ChickenLotSpecs.filter(filter), pageable);
		
		List<ChickenLotModel> chickenLotsModel = chickenLotModelAssembler
				.toCollectionModel(chickenLotsPage.getContent());
		
		Page<ChickenLotModel> chickenLotsModelPage = new PageImpl<>(chickenLotsModel, pageable, 
				chickenLotsPage.getTotalElements());
		
		return chickenLotsModelPage;
	}
	
	@GetMapping("/{chickenLotId}")
	public ChickenLotModel findById(@PathVariable Long chickenLotId) {
		ChickenLot chickenLot = chickenLotService.findById(chickenLotId);
		
		return chickenLotModelAssembler.toModel(chickenLot);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ChickenLotModel create(@RequestBody @Valid ChickenLotInput chickenLotInput) {
		try {
			ChickenLot chickenLot = chickenLotInputDisassembler.toDomainObject(chickenLotInput);
			chickenLot = chickenLotService.save(chickenLot);
		
			return chickenLotModelAssembler.toModel(chickenLot);
		} catch (ChickenLotNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@PutMapping("/{chickenLotId}")
	public ChickenLotModel update(@PathVariable Long chickenLotId,
			@RequestBody @Valid ChickenLotInput chickenLotInput) {
		
		try {
			ChickenLot chickenLotSaved = chickenLotService.findById(chickenLotId);
			chickenLotInputDisassembler.copyToDomainObject(chickenLotInput, chickenLotSaved);
			chickenLotSaved = chickenLotService.save(chickenLotSaved);
		
			return chickenLotModelAssembler.toModel(chickenLotSaved);
		} catch (ChickenLotNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@DeleteMapping("/{chickenLotId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long chickenLotId) {
		chickenLotService.deleteById(chickenLotId);
	}
	
}