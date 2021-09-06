package com.eggerp.infrastucture.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.eggerp.domain.filter.ChickenLineageFilter;
import com.eggerp.domain.model.ChickenLineage;


public class ChickenLineageSpecs {

	public static Specification<ChickenLineage> filter(ChickenLineageFilter filter) {
		return (root, query, builder) -> {
//			if (ChickenLineage.class.equals(query.getResultType())) {
//				root.fetch("lineage");
//			}
//			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getLineage() != null) {
				predicates.add(builder.like(
						builder.lower(root.get("lineage")), "%" + filter.getLineage().toLowerCase() + "%"));
			}		
			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}