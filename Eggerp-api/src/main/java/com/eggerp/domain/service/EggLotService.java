package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.EggLotNotFoundException;
import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.model.EggLot;
import com.eggerp.domain.repository.EggLotRepository;


@Service
public class EggLotService {

	private static final String MSG_EGG_LOT_IN_USE 
		= "Lote de Ovos de código %d não pode ser removido, pois está em uso";

	@Autowired
	private EggLotRepository eggLotRepository;
	
	@Transactional
	public EggLot save(EggLot eggLot) {
		
		return eggLotRepository.save(eggLot);
	}
	
	@Transactional
	public void deleteById(Long eggLotId) {
		try {
			eggLotRepository.deleteById(eggLotId);
			eggLotRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EggLotNotFoundException(eggLotId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_EGG_LOT_IN_USE, eggLotId));
		}
	}
	
	public EggLot findById(Long eggLotId) {
		return eggLotRepository.findById(eggLotId)
			.orElseThrow(() -> new EggLotNotFoundException(eggLotId));
	}
	
}