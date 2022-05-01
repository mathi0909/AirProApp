package com.airpro.flights.service;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.entity.model.Flights;
import com.airpro.common.exceptionhandler.SystemException;

public interface IAirProFlightService {
	public ResponseVO flights()throws SystemException;
	public ResponseVO flights(String flightCode)throws SystemException;
	public Flights flights(Flights Flights)throws SystemException;
	
}
