package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.ShedFilter;
import com.eggerp.domain.model.Shed;


public class ShedSpecs {

	public static Specification<Shed> filter(ShedFilter filter) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getName() != null) {
				predicates.add(builder.like(
						builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
			}				
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}