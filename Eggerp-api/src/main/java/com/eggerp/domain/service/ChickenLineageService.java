package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.ChickenLineageNotFoundException;
import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.model.ChickenLineage;
import com.eggerp.domain.repository.ChickenLineageRepository;


@Service
public class ChickenLineageService {

	private static final String MSG_CHICKEN_lINEAGE_IN_USE 
		= "Linhagem de código %d não pode ser removida, pois está em uso";

	@Autowired
	private ChickenLineageRepository chickenLineageRepository;
	
	@Transactional
	public ChickenLineage save(ChickenLineage chickenLineage) {
		return chickenLineageRepository.save(chickenLineage);
	}
	
	@Transactional
	public void deleteById(Long chickenLineageId) {
		try {
			chickenLineageRepository.deleteById(chickenLineageId);
			chickenLineageRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ChickenLineageNotFoundException(chickenLineageId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CHICKEN_lINEAGE_IN_USE, chickenLineageId));
		}
	}
	
	public ChickenLineage findById(Long chickenLineageId) {
		return chickenLineageRepository.findById(chickenLineageId)
			.orElseThrow(() -> new ChickenLineageNotFoundException(chickenLineageId));
	}
	
}