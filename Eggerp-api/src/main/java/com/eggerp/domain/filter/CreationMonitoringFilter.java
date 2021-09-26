package com.eggerp.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.eggerp.domain.model.ChickenLot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationMonitoringFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateWeekInitial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateWeekFinal;
	
	private ChickenLot chickenLot;

}
