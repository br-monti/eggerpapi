package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.ShedManufacturerNotFoundException;
import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.model.ShedManufacturer;
import com.eggerp.domain.repository.ShedManufacturerRepository;


@Service
public class ShedManufacturerService {

	private static final String MSG_SHED_MANUFACTURER_IN_USE 
		= "Fabricante de Aviário de código %d não pode ser removida, pois está em uso";

	@Autowired
	private ShedManufacturerRepository shedManufacturerRepository;
	
	@Transactional
	public ShedManufacturer save(ShedManufacturer shedManufacturer) {
		return shedManufacturerRepository.save(shedManufacturer);
	}
	
	@Transactional
	public void deleteById(Long shedManufacturerId) {
		try {
			shedManufacturerRepository.deleteById(shedManufacturerId);
			shedManufacturerRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ShedManufacturerNotFoundException(shedManufacturerId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_SHED_MANUFACTURER_IN_USE, shedManufacturerId));
		}
	}
	
	public ShedManufacturer findById(Long shedManufacturerId) {
		return shedManufacturerRepository.findById(shedManufacturerId)
			.orElseThrow(() -> new ShedManufacturerNotFoundException(shedManufacturerId));
	}
	
}