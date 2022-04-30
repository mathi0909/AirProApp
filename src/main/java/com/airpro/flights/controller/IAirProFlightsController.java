package com.airpro.flights.controller;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.exceptionhandler.SystemException;
import com.airpro.common.model.Flights;

public interface IAirProFlightsController {
	
	public ResponseVO flights() throws SystemException;
	public Flights flights(Flights Flights) throws SystemException;
	public ResponseVO flights( String flightCode) throws SystemException ;

}
