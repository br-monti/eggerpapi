package com.eggerp.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShedModel {
	

	private Long id;
	private String name;
	private String type;
	private int capacity;	
	private String model;		
	private ShedManufacturerModel shedManufacturer;

}
