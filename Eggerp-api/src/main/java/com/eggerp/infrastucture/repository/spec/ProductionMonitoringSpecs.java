package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.ProductionMonitoringFilter;
import com.eggerp.domain.model.ProductionMonitoring;
import com.eggerp.domain.model.ProductionMonitoring_;


public class ProductionMonitoringSpecs {

	public static Specification<ProductionMonitoring> filter(ProductionMonitoringFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getChickenLot() != null) {
				predicates.add(builder.and(
						builder.equal((root.get(ProductionMonitoring_.chickenLot)), filter.getChickenLot().getId())));
			}
			
			if (filter.getDateWeekInitial() != null) {
				predicates.add(
						builder.greaterThanOrEqualTo((root.get(ProductionMonitoring_.dateWeek)), filter.getDateWeekInitial()));
			}
			
			if (filter.getDateWeekFinal() != null) {
				predicates.add(
						builder.lessThanOrEqualTo((root.get(ProductionMonitoring_.dateWeek)), filter.getDateWeekFinal()));
			}		
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}