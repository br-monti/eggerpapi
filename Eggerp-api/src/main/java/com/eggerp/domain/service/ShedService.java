package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.exception.ShedNotFoundException;
import com.eggerp.domain.model.Shed;
import com.eggerp.domain.model.ShedManufacturer;
import com.eggerp.domain.repository.ShedRepository;


@Service
public class ShedService {

	private static final String MSG_SHED_IN_USE 
		= "Aviário de código %d não pode ser removida, pois está em uso";

	@Autowired
	private ShedRepository shedRepository;
	
	@Autowired
	private ShedManufacturerService shedManufacturerService;
	
	@Transactional
	public Shed save(Shed shed) {
		
		Long shedManufacturerId = shed.getShedManufacturer().getId();
		ShedManufacturer shedManufacturer = shedManufacturerService.findById(shedManufacturerId);
		
		shed.setShedManufacturer(shedManufacturer);
		return shedRepository.save(shed);
	}
	
	@Transactional
	public void deleteById(Long shedId) {
		try {
			shedRepository.deleteById(shedId);
			shedRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ShedNotFoundException(shedId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_SHED_IN_USE, shedId));
		}
	}
	
	public Shed findById(Long shedId) {
		return shedRepository.findById(shedId)
			.orElseThrow(() -> new ShedNotFoundException(shedId));
	}
	
}