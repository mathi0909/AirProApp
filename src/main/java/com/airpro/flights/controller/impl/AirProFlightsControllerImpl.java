package com.airpro.flights.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.exceptionhandler.ExceptionResolver;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.model.Flights;
import com.airpro.flights.controller.IAirProFlightsController;
import com.airpro.flights.service.IAirProFlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AirProFlightsControllerImpl implements IAirProFlightsController {

	IAirProFlightService airProFlightService;

	public AirProFlightsControllerImpl(IAirProFlightService airProFlightService) {
		this.airProFlightService = airProFlightService;
	}

	@GetMapping("/flights")
	public ResponseVO flights() throws SystemException {
		log.info("Enter flights ");
		try {
			return airProFlightService.flights();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists flights ");
		}
	}

	@GetMapping("/flights/{flightCode}")
	public ResponseVO flights(@PathVariable String flightCode) throws SystemException {
		log.info("Enter flights ");
		try {
			return airProFlightService.flights(flightCode);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists Get Flights by Flight Code ");
		}

	}

	@PostMapping("/flights")
	public Flights flights(@RequestBody Flights flight) throws SystemException {
		log.info("Enter save flights ");
		try {
			return airProFlightService.flights(flight);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ExceptionResolver.resolve(ex);
		} finally {
			log.info("Exists save flights  ");
		}

	}

}
