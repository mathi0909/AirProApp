package com.airpro.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.airpro.common.entity.dto.IFlightDTO;
import com.airpro.common.entity.model.Flights;

class AirProDefaultModelMapperTest {

	@Mock
	ModelMapper modelMapper;
	@InjectMocks
	AirProDefaultModelMapper airProDefaultModelMapper;
	IFlightDTO fightdto1;
	Flights expectedFlight;

	@BeforeEach
	void setUp() throws Exception {
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
				return "SYN";
			}

			@Override
			public String getArrivalPort() {
				// TODO Auto-generated method stub
				return "ML";
			}

			@Override
			public LocalDateTime getDepartureTime() {
				return LocalDate.now(ZoneId.of("UTC")).atStartOfDay();
			}

			@Override
			public LocalDateTime getArrivalTime() {
				// TODO Auto-generated method stub
				return LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX);
			}

		};
		expectedFlight = new Flights(null, "CX400", "SYN", "ML", "EK",
				LocalDate.now(ZoneId.of("UTC")).atStartOfDay(), LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
	}

	@Test
	@DisplayName("Check the object conversion")
	void testMapObject() {
	 

		when(modelMapper.map(anyObject(), anyObject())).thenReturn(expectedFlight);
		Flights rst = airProDefaultModelMapper.mapObject(fightdto1, Flights.class);
		assertEquals(expectedFlight, rst);

	}

	@Test
	void testMapList() {
		
		List<IFlightDTO> lst = new ArrayList<>();
		lst.add(fightdto1);
		
		 List<Flights> flightLst = new ArrayList<>();
		 flightLst.add(expectedFlight);
		 

			when(modelMapper.map(anyObject(), anyObject())).thenReturn(expectedFlight);
			List<Flights> rst = airProDefaultModelMapper.mapList(lst, Flights.class);
			
			assertEquals(1,rst.size());
		 
		 
	}

}
