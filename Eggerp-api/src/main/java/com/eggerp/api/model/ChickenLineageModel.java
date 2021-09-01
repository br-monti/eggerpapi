package com.eggerp.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChickenLineageModel {
	
	private Long id;
	private String lineage;
	private String chickenColor;
	private String provider;

}
