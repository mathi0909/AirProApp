package com.airpro.flights.service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.model.Flights;

public interface IAirProFlightService {
	public ResponseVO flights()throws SystemException;
	public ResponseVO flights(String flightCode)throws SystemException;
	public Flights flights(Flights Flights)throws SystemException;
	
}
