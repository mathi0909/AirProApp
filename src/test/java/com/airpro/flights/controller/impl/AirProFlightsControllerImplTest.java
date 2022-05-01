package com.airpro.flights.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.time.LocalDate;
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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.entity.model.Flights;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.flights.service.impl.AirProFlightServiceImpl;

class AirProFlightsControllerImplTest {

	@LocalServerPort
	private int port;
 
	
	@Mock
	AirProFlightServiceImpl airProFlightService;
	
	@InjectMocks
	AirProFlightsControllerImpl airProFlightsController;
	
	Flights f1 ;
	Flights f2 ;
	List<Flights> lst = new ArrayList<>();
	ResponseVO responseVO;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		f1 = new Flights(UUID.randomUUID(), "QF400", "MEL", "SYD", "QF",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		f2 = new Flights(UUID.randomUUID(),"CX654", "SYD", "MEL", "CX",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		responseVO= new ResponseVO();
		lst.add(f2);
		lst.add(f1);
		responseVO.setFlights(lst);
	}

//	@Test
//	void testAirProFlightsControllerImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("To check the controller to fetch all the flights")
	void testFlights() throws Exception {
 
		when(airProFlightService.flights()).thenReturn(responseVO);
		assertEquals(responseVO,airProFlightsController.flights());
		
	}

	@Test
	@DisplayName("To check the controller to fetch the flights by flight code")
	void testFlightsByFlightCode() throws Exception {
		when(airProFlightService.flights(anyString())).thenReturn(responseVO);
		assertEquals(responseVO,airProFlightsController.flights(anyString()));
	}

	@Test
	@DisplayName("To check the controller to save the flight")
	void testFlightsFlights() throws Exception {
		when(airProFlightService.flights(f1)).thenReturn(f1);
		assertEquals(f1,airProFlightsController.flights(f1));
	}

}
