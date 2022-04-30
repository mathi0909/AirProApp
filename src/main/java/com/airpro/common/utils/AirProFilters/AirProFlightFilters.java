package com.airpro.common.utils.AirProFilters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.airpro.common.model.Flights;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AirProFlightFilters {
	
	AirProFilter airProFilter;
	AirProFlightFilters(AirProFilter airProFilter){
		this.airProFilter  = airProFilter;
	}

	public AirProFilter filter() {
		airProFilter.setStartDate(LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		airProFilter.setEndDate(LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		 
		return airProFilter;
	}
	
	public AirProFilter filter(String flightCode) {
		airProFilter.setFlightCode(flightCode);
		airProFilter.setStartDate(LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		airProFilter.setEndDate(LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		return airProFilter;
	}
	
	
	
}
