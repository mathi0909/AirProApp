package com.airpro.flights.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.exceptionhandler.BusinessException;
import com.airpro.common.exceptionhandler.ExceptionResolver;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.model.Flights;
import com.airpro.common.utils.AirProDefaultModelMapper;
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
	Environment env;
	AirProDefaultModelMapper airProDefaultModelMapper;

	public AirProFlightServiceImpl(IAirProFlightsDao airProFlightsDao, AirProFlightFilters airProFlightFilters,
			Environment env,AirProDefaultModelMapper airProDefaultModelMapper) {
		this.airProFlightsDao = airProFlightsDao;
		this.airProFlightFilters = airProFlightFilters;
		this.env = env;
		this.airProDefaultModelMapper = airProDefaultModelMapper;
	}

	@Override
	public ResponseVO flights() throws SystemException {
		log.info("Enter get flights ");
		try {
			AirProFilter currentDayFilter = airProFlightFilters.filter();
			ResponseVO responseVO = new ResponseVO();
			responseVO.setFlights(airProDefaultModelMapper.mapList(airProFlightsDao.findByDepartureTimeBetween(currentDayFilter.getStartDate(),
					currentDayFilter.getEndDate()), Flights.class));
			return responseVO;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists get flights ");
		}

	}

	@Override
	public Flights flights(Flights flights) throws SystemException {
		log.info("Enter save flights ");
		try {
			return airProFlightsDao.save(flights);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists save flights ");
		}

	}

	@Override
	public ResponseVO flights(String flightCode) throws SystemException {
		log.info("Enter Get Flights by Flight Code ");
		try {
			AirProFilter flightCodeFilter = airProFlightFilters.filter(flightCode);

			if (!airProFlightsDao.existsByFlightCodeIgnoreCase(flightCode)) {
				throw new BusinessException("Flight Code Doesn't Exists", HttpStatus.BAD_REQUEST);
			}

			ResponseVO responseVO = new ResponseVO();
			
			responseVO.setFlights(airProDefaultModelMapper.mapList(airProFlightsDao.findByDepartureTimeBetweenAndFlightCodeIgnoreCase(
					flightCodeFilter.getStartDate(), flightCodeFilter.getEndDate(), flightCodeFilter.getFlightCode()),Flights.class));
			
			return responseVO;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists Get Flights by Flight Code ");
		}

	}

}
