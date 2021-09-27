package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.CreationMonitoringFilter;
import com.eggerp.domain.model.CreationMonitoring;
import com.eggerp.domain.model.CreationMonitoring_;


public class CreationMonitoringSpecs {

	public static Specification<CreationMonitoring> filter(CreationMonitoringFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getChickenLot() != null) {
				predicates.add(builder.and(
						builder.equal((root.get(CreationMonitoring_.chickenLot)), filter.getChickenLot().getId())));
			}
			
			if (filter.getDateWeekInitial() != null) {
				predicates.add(
						builder.greaterThanOrEqualTo((root.get(CreationMonitoring_.dateWeek)), filter.getDateWeekInitial()));
			}
			
			if (filter.getDateWeekFinal() != null) {
				predicates.add(
						builder.lessThanOrEqualTo((root.get(CreationMonitoring_.dateWeek)), filter.getDateWeekFinal()));
			}		
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}