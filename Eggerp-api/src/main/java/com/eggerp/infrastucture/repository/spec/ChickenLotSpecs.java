package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.ChickenLotFilter;
import com.eggerp.domain.model.ChickenLot;
import com.eggerp.domain.model.ChickenLot_;


public class ChickenLotSpecs {

	public static Specification<ChickenLot> filter(ChickenLotFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getId() != null) {
				predicates.add(builder.and(
						builder.equal((root.get(ChickenLot_.id)), filter.getId())));
			}
			
			if (filter.getBirthDateInitial() != null) {
				predicates.add(
						builder.greaterThanOrEqualTo((root.get(ChickenLot_.birthDate)), filter.getBirthDateInitial()));
			}
			
			if (filter.getBirthDateFinal() != null) {
				predicates.add(
						builder.lessThanOrEqualTo((root.get(ChickenLot_.birthDate)), filter.getBirthDateFinal()));
			}
			
			if (filter.getShed() != null) {
				predicates.add(builder.and(
						builder.equal((root.get(ChickenLot_.shed)), filter.getShed().getId())));
			}			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}