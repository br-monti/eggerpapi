package com.eggerp.api.model;

import java.util.List;

import com.eggerp.domain.model.ChickenLot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EggLotModel {
	

	private Long id;
	private String name;
	private String boxColor;
	private List<ChickenLot> chickenLots;

}
