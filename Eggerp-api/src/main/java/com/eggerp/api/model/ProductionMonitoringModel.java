package com.eggerp.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductionMonitoringModel {	

	private Long id;
	private int ageWeek;
	private int ageDay;
	private LocalDate dateWeek;	
	private int bodyWeight;	
	private int food;	
	private int water;	
	private int discard;	
	private int mortality;
	private int totalProduction;
	private int firstEggs;
	private int secondEggs;
	private int eggWeight;
	private ChickenLotModel chickenLot;

}
