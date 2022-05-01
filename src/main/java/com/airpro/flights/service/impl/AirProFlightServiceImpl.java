package com.airpro.flights.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.entity.model.Flights;
import com.airpro.common.exceptionhandler.BusinessException;
import com.airpro.common.exceptionhandler.ExceptionResolver;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.utils.AirProDefaultModelMapper;
import com.airpro.common.utils.AirProFilters.AirProFilter;
import com.airpro.common.utils.AirProFilters.AirProFlightFilters;
import com.airpro.flights.repo.IAirProFlightsDao;
import com.airpro.flights.service.IAirProFlightService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <em>AirPro Service Layer for Flight enquires </em>
 * 
 * <p>
 * Class contains all the required business logic to cater the flight enquires.
 * <br>
 * This repository for the Flights is injected into this class. <b> JPA </b> is
 * used to do all CRUD operations
 * </p>
 * 
 * @author Mathi
 * @version 1.0
 *
 */

@Service
@Slf4j
public class AirProFlightServiceImpl implements IAirProFlightService {

	IAirProFlightsDao airProFlightsDao;
	AirProFlightFilters airProFlightFilters;
	Environment env;
	AirProDefaultModelMapper airProDefaultModelMapper;

	public AirProFlightServiceImpl(IAirProFlightsDao airProFlightsDao, AirProFlightFilters airProFlightFilters,
			Environment env, AirProDefaultModelMapper airProDefaultModelMapper) {
		this.airProFlightsDao = airProFlightsDao;
		this.airProFlightFilters = airProFlightFilters;
		this.env = env;
		this.airProDefaultModelMapper = airProDefaultModelMapper;
	}

	/**
	 * 
	 * <em>Gets all the flights for the current day. Returns the list of flights
	 * available for today<em>
	 * 
	 * @return ResponseVO
	 */
	@Override
	public ResponseVO flights() throws SystemException {
		log.info("Enter get flights ");
		try {
			AirProFilter currentDayFilter = airProFlightFilters.filter();
			ResponseVO responseVO = new ResponseVO();
			// DTO & Projections are used to improve the performance by reading only the
			// selected columns
			responseVO.setFlights(airProDefaultModelMapper.mapList(airProFlightsDao.findByDepartureTimeBetween(
					currentDayFilter.getStartDate(), currentDayFilter.getEndDate()), Flights.class));
			return responseVO;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists get flights ");
		}

	}

	/**
	 * Adds a new flight into the DB.
	 *
	 * @param flightCode
	 * @return Flights
	 */
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

	/**
	 *
	 * <em>Gets all the flights for the given flight code and current day. Returns the list of flights
	 * available for today<em>
	 * 
	 * @param flightCode
	 * @return ResponseVO
	 *
	 */
	@Override
	public ResponseVO flights(String flightCode) throws SystemException {
		log.info("Enter Get Flights by Flight Code ");
		try {
			
			if (!airProFlightsDao.existsByFlightCodeIgnoreCase(flightCode)) {
				throw new BusinessException(env.getProperty("error.air.pro.invalid.flight.code.msg"),
						HttpStatus.BAD_REQUEST);
			}
			
			AirProFilter flightCodeFilter = airProFlightFilters.filter(flightCode);

			ResponseVO responseVO = new ResponseVO();

			responseVO.setFlights(airProDefaultModelMapper.mapList(
					airProFlightsDao.findByDepartureTimeBetweenAndFlightCodeIgnoreCase(flightCodeFilter.getStartDate(),
							flightCodeFilter.getEndDate(), flightCodeFilter.getFlightCode()),
					Flights.class));

			return responseVO;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists Get Flights by Flight Code ");
		}

	}

}
