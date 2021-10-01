package com.eggerp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eggerp.domain.exception.EntityInUseException;
import com.eggerp.domain.exception.ShedNotFoundException;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.ProductionMonitoring;
import com.eggerp.domain.repository.ProductionMonitoringRepository;


@Service
public class ProductionMonitoringService {

	private static final String MSG_PRODUCTION_MONITORING_IN_USE 
		= "Monitoramento de Produção de código %d não pode ser removido, pois está em uso";

	@Autowired	
	private ProductionMonitoringRepository productionMonitoringRepository;
		
	@Autowired	
	private ChickenLotService chickenLotService;
	
	@Transactional
	public ProductionMonitoring save(ProductionMonitoring productionMonitoring) {
		
		Long chickenLotId = productionMonitoring.getChickenLot().getId();
		ChickenLot chickenLot = chickenLotService.findById(chickenLotId);
		
				
		productionMonitoring.setChickenLot(chickenLot);
		
		return productionMonitoringRepository.save(productionMonitoring);
	}
	
	@Transactional
	public void deleteById(Long productionMonitoringId) {
		try {
			productionMonitoringRepository.deleteById(productionMonitoringId);
			productionMonitoringRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ShedNotFoundException(productionMonitoringId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_PRODUCTION_MONITORING_IN_USE, productionMonitoringId));
		}
	}
	
	public ProductionMonitoring findById(Long productionMonitoring) {
		return productionMonitoringRepository.findById(productionMonitoring)
			.orElseThrow(() -> new ShedNotFoundException(productionMonitoring));
	}
	
}