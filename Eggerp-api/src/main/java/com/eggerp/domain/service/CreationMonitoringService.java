package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.exception.ShedNotFoundException;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.CreationMonitoring;
import com.eggerp.domain.repository.CreationMonitoringRepository;


@Service
public class CreationMonitoringService {

	private static final String MSG_CREATION_MONITORING_IN_USE 
		= "Monitoramento de Criação de código %d não pode ser removido, pois está em uso";

	@Autowired	
	private CreationMonitoringRepository creationMonitoringRepository;
		
	@Autowired	
	private ChickenLotService chickenLotService;
	
	@Transactional
	public CreationMonitoring save(CreationMonitoring creationMonitoring) {
		
		Long chickenLotId = creationMonitoring.getChickenLot().getId();
		ChickenLot chickenLot = chickenLotService.findById(chickenLotId);
		
				
		creationMonitoring.setChickenLot(chickenLot);
		
		return creationMonitoringRepository.save(creationMonitoring);
	}
	
	@Transactional
	public void deleteById(Long creationMonitoringId) {
		try {
			creationMonitoringRepository.deleteById(creationMonitoringId);
			creationMonitoringRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ShedNotFoundException(creationMonitoringId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CREATION_MONITORING_IN_USE, creationMonitoringId));
		}
	}
	
	public CreationMonitoring findById(Long creationMonitoring) {
		return creationMonitoringRepository.findById(creationMonitoring)
			.orElseThrow(() -> new ShedNotFoundException(creationMonitoring));
	}
	
}