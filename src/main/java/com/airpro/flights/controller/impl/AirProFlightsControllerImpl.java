package com.airpro.flights.controller.impl;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airpro.common.constant.CommonConstant;
import com.airpro.common.domain.ResponseVO;
import com.airpro.common.entity.model.Flights;
import com.airpro.common.exceptionhandler.ExceptionResolver;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.flights.controller.IAirProFlightsController;
import com.airpro.flights.service.IAirProFlightService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name="AirPro Flights Controller",description="API definition for AirPro Flights")
public class AirProFlightsControllerImpl implements IAirProFlightsController {

	IAirProFlightService airProFlightService;

	public AirProFlightsControllerImpl(IAirProFlightService airProFlightService) {
		this.airProFlightService = airProFlightService;
	}

	@GetMapping("/flights")
	@Operation(summary=CommonConstant.SWAGGER_GET_ALL_FLIGHTS_API_SUMMARY,description=CommonConstant.SWAGGER_GET_ALL_FLIGHTS_API_DESCRIPTION)
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
	@Operation(summary=CommonConstant.SWAGGER_GET_ALL_FLIGHTS_BY_CODE_API_SUMMARY,description=CommonConstant.SWAGGER_GET_ALL_FLIGHTS_BY_CODE_API_DESCRIPTION)
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
	@Operation(summary=CommonConstant.SWAGGER_ADD_FLIGHTS_BY_CODE_API_SUMMARY,description=CommonConstant.SWAGGER_ADD_FLIGHTS_API_DESCRIPTION)
	public Flights flights(@Valid  @RequestBody Flights flight) throws SystemException {
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
