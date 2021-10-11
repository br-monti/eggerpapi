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

import com.eggerp.api.assembler.EggLotInputDisassembler;
import com.eggerp.api.assembler.EggLotModelAssembler;
import com.eggerp.api.model.EggLotModel;
import com.eggerp.api.model.input.EggLotInput;
import com.eggerp.domain.exception.EggLotNotFoundException;
import com.eggerp.domain.exception.TransactionalException;
import com.eggerp.domain.filter.EggLotFilter;
import com.eggerp.domain.model.EggLot;
import com.eggerp.domain.repository.EggLotRepository;
import com.eggerp.domain.service.EggLotService;
import com.eggerp.infrastucture.repository.spec.EggLotSpecs;

@RestController
@RequestMapping(value = "/EggLots")
public class EggLotController {

	@Autowired
	private EggLotRepository eggLotRepository;
	
	@Autowired
	private EggLotService eggLotService;
	
	@Autowired
	private EggLotModelAssembler eggLotModelAssembler;
	
	@Autowired
	private EggLotInputDisassembler eggLotInputDisassembler;
	
	@GetMapping
	public Page<EggLotModel> find(EggLotFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<EggLot> eggLotsPage = eggLotRepository.findAll(EggLotSpecs.filter(filter), pageable);
		
		List<EggLotModel> eggLotsModel = eggLotModelAssembler
				.toCollectionModel(eggLotsPage.getContent());
		
		Page<EggLotModel> eggLotsModelPage = new PageImpl<>(eggLotsModel, pageable, 
				eggLotsPage.getTotalElements());
		
		return eggLotsModelPage;
	}
	
	@GetMapping("/{eggLotId}")
	public EggLotModel findById(@PathVariable Long eggLotId) {
		EggLot eggLot = eggLotService.findById(eggLotId);
		
		return eggLotModelAssembler.toModel(eggLot);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EggLotModel create(@RequestBody @Valid EggLotInput eggLotInput) {
		try {
			EggLot eggLot = eggLotInputDisassembler.toDomainObject(eggLotInput);
			eggLot = eggLotService.save(eggLot);
		
			return eggLotModelAssembler.toModel(eggLot);
		} catch (EggLotNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@PutMapping("/{eggLotId}")
	public EggLotModel update(@PathVariable Long eggLotId,
			@RequestBody @Valid EggLotInput eggLotInput) {
		
		try {
			EggLot eggLotSaved = eggLotService.findById(eggLotId);
			eggLotInputDisassembler.copyToDomainObject(eggLotInput, eggLotSaved);
			eggLotSaved = eggLotService.save(eggLotSaved);
		
			return eggLotModelAssembler.toModel(eggLotSaved);
		} catch (EggLotNotFoundException e) {
			throw new TransactionalException(e.getMessage());
	}
		
	}
	
	@DeleteMapping("/{eggLotId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long eggLotId) {
		eggLotService.deleteById(eggLotId);
	}
	
}