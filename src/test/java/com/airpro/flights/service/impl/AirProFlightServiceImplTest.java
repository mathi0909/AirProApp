package com.airpro.flights.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.dto.IFlightDTO;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.model.Flights;
import com.airpro.common.utils.AirProDefaultModelMapper;
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
	@Mock
	ModelMapper modelMapper;
	
	@Mock
	AirProDefaultModelMapper airProDefaultModelMapper;

	@InjectMocks
	AirProFlightServiceImpl airProFlightServiceImpl;
	
	IFlightDTO fightdto1;
	IFlightDTO fightdto2;
	List<IFlightDTO> lstDto = new ArrayList<>();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		 fightdto1 = new IFlightDTO() {

				@Override
				public String getFlightNumber() {
					// TODO Auto-generated method stub
					return "CX400";
				}

				@Override
				public String getDeparturePort() {
					// TODO Auto-generated method stub
					return "MEL";
				}

				@Override
				public String getArrivalPort() {
					// TODO Auto-generated method stub
					return "SYD";
				}
 

				@Override
				public LocalDateTime getDepartureTime() {
					// TODO Auto-generated method stub
					return LocalDate.now(ZoneId.of("UTC")).atStartOfDay();
				}

				@Override
				public LocalDateTime getArrivalTime() {
					// TODO Auto-generated method stub
					return  LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX);
				}
				
			};
			fightdto2 = new IFlightDTO() {

				@Override
				public String getFlightNumber() {
					// TODO Auto-generated method stub
					return "QF500";
				}

				@Override
				public String getDeparturePort() {
					// TODO Auto-generated method stub
					return "MEL";
				}

				@Override
				public String getArrivalPort() {
					// TODO Auto-generated method stub
					return "SYD";
				}
 

				@Override
				public LocalDateTime getDepartureTime() {
					// TODO Auto-generated method stub
					return LocalDate.now(ZoneId.of("UTC")).atStartOfDay();
				}

				@Override
				public LocalDateTime getArrivalTime() {
					// TODO Auto-generated method stub
					return  LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX);
				}
				
			};
			lstDto.add(fightdto1);
			lstDto.add(fightdto2);
		
	
	}

	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Test to get the current day flights")
	void testFetchFlights 	() throws SystemException {
		Flights f1 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		Flights f2 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		List<Flights> lst = new ArrayList<>();
		lst.add(f2);
		lst.add(f1);

		AirProFilter filter = new AirProFilter("EQ", LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX),
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay());

		
		when(airProFlightFilters.filter()).thenReturn(filter);
		when(airProFlightsDao.findByDepartureTimeBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
				.thenReturn(lstDto);
		
		when(airProDefaultModelMapper.mapList((lstDto), Flights.class)).thenReturn(lst);

		Integer count = airProFlightServiceImpl.flights().getFlights().size();
		assertEquals(2, count);
	}

	@Test
	@DisplayName("Test for invalid flight code")
	void testInvalidFlightCode() throws SystemException {
//		Flights f1 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
//				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
//		Flights f2 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
//				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
//		List<Flights> lst = new ArrayList<>();
//		lst.add(f2);
//		lst.add(f1);

		AirProFilter filter = new AirProFilter("CX", LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX),
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		
		when(airProFlightFilters.filter("CX")).thenReturn(filter);
		when(airProFlightsDao.existsByFlightCodeIgnoreCase(anyString())).thenReturn(false);
		when(airProFlightsDao.findByDepartureTimeBetweenAndFlightCodeIgnoreCase(
				any(LocalDateTime.class), any(LocalDateTime.class),anyString())).thenReturn(lstDto);
		
//		assertEquals(2,  airProFlightServiceImpl.flights("cq").getFlights().size());
		
		assertThrows(SystemException.class, ()->{
			 airProFlightServiceImpl.flights(anyString());
		 },"Flight Code Doesn't Exists should be thrown");

	}
	
	@Test
	@DisplayName("Test for invalid flight code")
	void testFetchFlightsByFlightCode() throws SystemException {
		Flights f1 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		Flights f2 = new Flights(UUID.randomUUID(), "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		List<Flights> lst = new ArrayList<>();
		lst.add(f2);
		lst.add(f1);

		AirProFilter filter = new AirProFilter("CX", LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX),
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		
		when(airProFlightFilters.filter(anyString())).thenReturn(filter);
		when(airProFlightsDao.existsByFlightCodeIgnoreCase(anyString())).thenReturn(true);
		when(airProFlightsDao.findByDepartureTimeBetweenAndFlightCodeIgnoreCase(
				any(LocalDateTime.class), any(LocalDateTime.class),anyString())).thenReturn(lstDto);
		 
		when(airProDefaultModelMapper.mapList((lstDto), Flights.class)).thenReturn(lst);

		
		assertEquals(2,  airProFlightServiceImpl.flights(anyString()).getFlights().size());

	}
	
	
	@Test
	@DisplayName("Test Save Flights")
	void testSaveFilghts() throws SystemException {
		UUID id =UUID.randomUUID();
		Flights f1 = new Flights(id, "asd", "123", "123", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));	
		when(airProFlightsDao.save(f1)).thenReturn(f1);
		assertEquals(id, airProFlightServiceImpl.flights(f1).getFlightId());
	}
 
}


