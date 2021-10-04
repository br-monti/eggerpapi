package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.EggLotFilter;
import com.eggerp.domain.model.EggLot;
import com.eggerp.domain.model.EggLot_;


public class EggLotSpecs {

	public static Specification<EggLot> filter(EggLotFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getId() != null) {
				predicates.add(builder.and(
						builder.equal((root.get(EggLot_.id)), filter.getId())));
			}
			
			if (!(filter.getName().isEmpty())) {
				predicates.add(builder.like(
						builder.lower(root.get(EggLot_.name)), "%" + filter.getName().toLowerCase() + "%"));
			}	
			
			if (!(filter.getBoxColor().isEmpty())) {
				predicates.add(builder.like(
						builder.lower(root.get(EggLot_.boxColor)), "%" + filter.getBoxColor().toLowerCase() + "%"));
			}		
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}