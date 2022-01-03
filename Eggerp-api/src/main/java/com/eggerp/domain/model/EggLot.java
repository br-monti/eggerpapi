package com.eggerp.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "egg_lot")
public class EggLot {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JoinColumn(name = "box_color")
	private String boxColor;

	//@OneToMany(mappedBy = "eggLot", cascade = CascadeType.ALL)
	@OneToMany
	@JoinColumn(name = "egg_lot_id")
	private List<ChickenLot> chickenLots;
		
}
