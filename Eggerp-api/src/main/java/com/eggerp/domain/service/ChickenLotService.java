package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.exception.ShedNotFoundException;
import com.eggerp.domain.model.ChickenLineage;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.Shed;
import com.eggerp.domain.repository.ChickenLotRepository;


@Service
public class ChickenLotService {

	private static final String MSG_CHICKEN_lOT_IN_USE 
		= "Lote de Aves de código %d não pode ser removido, pois está em uso";

	@Autowired	
	private ChickenLotRepository chickenLotRepository;
	
	@Autowired
	private ChickenLineageService chickenLineageService;
	
	@Autowired	
	private ShedService shedService;
	
	@Transactional
	public ChickenLot save(ChickenLot chickenLot) {
		
		Long shedId = chickenLot.getShed().getId();
		Shed shed = shedService.findById(shedId);
		
		Long chickenLineageId = chickenLot.getChickenLineage().getId();
		ChickenLineage chickenLineage = chickenLineageService.findById(chickenLineageId);
		
		chickenLot.setShed(shed);
		chickenLot.setChickenLineage(chickenLineage);
		
		return chickenLotRepository.save(chickenLot);
	}
	
	@Transactional
	public void deleteById(Long chickenLotId) {
		try {
			chickenLotRepository.deleteById(chickenLotId);
			chickenLotRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ShedNotFoundException(chickenLotId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CHICKEN_lOT_IN_USE, chickenLotId));
		}
	}
	
	public ChickenLot findById(Long chickenLot) {
		return chickenLotRepository.findById(chickenLot)
			.orElseThrow(() -> new ShedNotFoundException(chickenLot));
	}
	
}