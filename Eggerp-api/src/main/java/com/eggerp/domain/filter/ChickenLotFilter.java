package com.eggerp.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.eggerp.domain.model.Shed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChickenLotFilter {

	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDateInitial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDateFinal;
	
	private Shed shed;
	
}
