package com.airpro.common.utils.AirProFilters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class AirProFlightFiltersTest {

	 
	AirProFilter filter=new AirProFilter();
 
	 
	AirProFlightFilters airProFlightFilters = new AirProFlightFilters(filter);
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("To check the default AirPro Filter")
	void testAirProFlightFilters() {

		AirProFilter defaultFilter =new  AirProFilter(null, LocalDate.now(ZoneId.of("UTC")).atStartOfDay(),
 			LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		 
		assertEquals(defaultFilter, airProFlightFilters.filter());

	}

	@Test
	void testFilterString() {
		AirProFilter flightCodeFilter =new  AirProFilter("CX", LocalDate.now(ZoneId.of("UTC")).atStartOfDay(),
	 			LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		assertEquals(flightCodeFilter, airProFlightFilters.filter("CX"));
	}

}
