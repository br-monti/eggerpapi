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
@Table(name = "chicken_lot")
public class ChickenLot {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "accommodation_date")
	private LocalDate accommodationDate;

	@Column(name = "initial_quantity")
	private int initialQuantity;

	@Column(name = "current_quantity")
	private int currentQuantity;
	
	//@NotNull
	private String debicking;

	@ManyToOne
	@JoinColumn(name = "chicken_lineage_id")
	private ChickenLineage chickenLineage;

	@ManyToOne
	@JoinColumn(name = "shed_id")
	private Shed shed;
	
//	//@NotNull
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	private EggLot eggLot;

}
