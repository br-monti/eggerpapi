package com.eggerp.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "shed")
public class Shed {
	
	@NotNull
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
		
	@NotBlank
	private String type;
	
	@NotNull
	private int capacity;
	
	@NotBlank
	private String model;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "shed_manufacturer_id")
	private ShedManufacturer shedManufacturer;

}
