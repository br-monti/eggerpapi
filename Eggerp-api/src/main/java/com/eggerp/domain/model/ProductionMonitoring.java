package com.eggerp.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "production_monitoring")
public class ProductionMonitoring {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "age_week")
	private int ageWeek;
	
	@Column(name = "age_day")
	private int ageDay;
	
	@Column(name = "date_week")
	private LocalDate dateWeek;
		
	@Column(name = "body_weight")
	private int bodyWeight;
	
	private int food;
	
	private int water;
	
	private int discard;
	
	private int mortality;
	
	@Column(name = "total_production")
	private int totalProduction;
	
	@Column(name = "first_eggs")
	private int firstEggs;
	
	@Column(name = "second_eggs")
	private int secondEggs;
	
	@Column(name = "egg_weight")
	private int eggWeight;
	
	@ManyToOne
	@JoinColumn(name = "chicken_lot_id")
	private ChickenLot chickenLot;

}
