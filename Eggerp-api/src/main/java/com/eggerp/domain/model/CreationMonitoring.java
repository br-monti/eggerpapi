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
@Table(name = "creation_monitoring")
public class CreationMonitoring {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "age_week")
	private int ageWeek;
	
	@NotNull
	@Column(name = "age_day")
	private int ageDay;
	
	@NotNull
	@Column(name = "date_week")
	private LocalDate dateWeek;
		
	@NotNull
	@Column(name = "body_weight")
	private int bodyWeight;
	
	@NotNull
	private int food;
	
	@NotNull
	private int water;
	
	@NotNull
	private int discard;
	
	@NotNull
	private int mortality;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "chicken_lot_id")
	private ChickenLot chickenLot;

}
