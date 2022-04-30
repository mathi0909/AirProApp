package com.airpro.flights.service.impl;

import org.springframework.stereotype.Service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.model.Flights;
import com.airpro.common.utils.AirProFilters.AirProFilter;
import com.airpro.common.utils.AirProFilters.AirProFlightFilters;
import com.airpro.flights.repo.IAirProFlightsDao;
import com.airpro.flights.service.IAirProFlightService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AirProFlightServiceImpl implements IAirProFlightService {
	
	IAirProFlightsDao airProFlightsDao;
	AirProFlightFilters airProFlightFilters;
 
	
	
	public AirProFlightServiceImpl (IAirProFlightsDao airProFlightsDao,AirProFlightFilters airProFlightFilters ) {
		this.airProFlightsDao =  airProFlightsDao;
		this.airProFlightFilters = airProFlightFilters;
	 
	}

	@Override
	public ResponseVO flights() {
		log.info("Enter get flights ");
		AirProFilter currentDayFilter =airProFlightFilters.filter();
		ResponseVO responseVO = new ResponseVO();
		responseVO.setFlights(airProFlightsDao.findByDepartureTimeBetween(currentDayFilter.getStartDate(),currentDayFilter.getEndDate()));
		return responseVO;
	}

	@Override
	public Flights flights(Flights Flights) {
		log.info("Enter save flights ");
		return airProFlightsDao.save(Flights);
	}

	@Override
	public ResponseVO flights(String flightCode) {
		log.info("Enter get flights ");
		AirProFilter flightCodeFilter = airProFlightFilters.filter(flightCode);
		
		if(!airProFlightsDao.existsByFlightCodeIgnoreCase(flightCode)) {
			// TODO - Throw exception when flight code does not present in the DB.
		}
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setFlights(
				airProFlightsDao.findByDepartureTimeBetweenAndFlightCodeIgnoreCase(
						flightCodeFilter.getStartDate(),flightCodeFilter.getEndDate(),flightCodeFilter.getFlightCode()));
		return responseVO;
	}

}
