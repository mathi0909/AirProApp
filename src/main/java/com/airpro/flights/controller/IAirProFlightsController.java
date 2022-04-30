package com.airpro.flights.controller;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.model.Flights;

public interface IAirProFlightsController {
	
	public ResponseVO flights();
	public Flights flights(Flights Flights);
	public ResponseVO flights( String flightCode);

}
