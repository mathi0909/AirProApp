package com.airpro.flights.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.model.Flights;
import com.airpro.common.utils.AirProFilters.AirProFilter;
import com.airpro.common.utils.AirProFilters.AirProFlightFilters;
import com.airpro.flights.repo.IAirProFlightsDao;
 
class AirProFlightServiceImplTest {

	@Mock
	IAirProFlightsDao airProFlightsDao;
	@Mock
	AirProFlightFilters airProFlightFilters;
	@Mock
	ResponseVO responseVO;

	@InjectMocks
	AirProFlightServiceImpl airProFlightServiceImpl ;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("Test Mock helloService + helloRepository")
	void test() {
		

		Flights f1 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		Flights f2 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		 List<Flights> lst = new ArrayList<>();
		 lst.add(f2);
		 lst.add(f1);
		 
		 AirProFilter filter = new AirProFilter("EQ", LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX),LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		 
//		 
//		 IAirProFlightsDao b = Mockito.mock(IAirProFlightsDao.class);
//		 AirProFlightFilters a = Mockito.mock(AirProFlightFilters.class); 
//		 ResponseVO res = Mockito.mock(ResponseVO.class);  
//		 when(a.filter()).thenReturn(filter);
//		 when(b.findByDepartureTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class) ))
//			.thenReturn(lst);
		 
		 when(airProFlightFilters.filter()).thenReturn(filter);
		 when(airProFlightsDao.findByDepartureTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class) ))
			.thenReturn(lst);
		 
		 ResponseVO res2 =  airProFlightServiceImpl.flights();
		 
		  
//		  c.flights();
//		  Mockito.verify( c  ).flights();
		  assertEquals(2, airProFlightServiceImpl.flights().getFlights().size());

//		 airProFlightsDao.findByDepartureTimeBetween(LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		
//		assertEquals(2, airProFlightServiceImpl.flights().getFlights().size());
//		fail("Not yet implemented");
	}

}
