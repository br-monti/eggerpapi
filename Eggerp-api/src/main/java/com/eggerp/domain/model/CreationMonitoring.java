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
	
	@ManyToOne
	@JoinColumn(name = "chicken_lot_id")
	private ChickenLot chickenLot;

}
