package com.airpro.flights.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airpro.common.domain.ResponseVO;
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
	public ResponseVO flights() {
		 log.info("Enter flights ");
		return  airProFlightService.flights();
	} 
	
	@GetMapping("/flights/{flightCode}")
	public ResponseVO flights(@PathVariable String flightCode) {
		 log.info("Enter flights ");
		return  airProFlightService.flights(flightCode);
	} 
	
	@PostMapping("/flights")
	public Flights flights( @RequestBody Flights flight) {
		 log.info("Enter flights ");
		return  airProFlightService.flights(flight);
	} 


}
