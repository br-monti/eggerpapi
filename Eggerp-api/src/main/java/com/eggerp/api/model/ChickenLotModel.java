package com.eggerp.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChickenLotModel {
	

	private Long id;
	private LocalDate birthDate;
	private LocalDate accommodationDate;
	private int initialQuantity;	
	private int currentQuantity;		
	private String debicking;
	private ChickenLineageModel chickenLineage;
	private ShedModel shed;

}
