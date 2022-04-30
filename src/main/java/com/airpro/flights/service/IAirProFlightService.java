package com.airpro.flights.service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.model.Flights;

public interface IAirProFlightService {
	public ResponseVO flights();
	public ResponseVO flights(String flightCode);
	public Flights flights(Flights Flights);
	
}
