package com.airpro.common.dto;

import java.time.LocalDateTime;

public interface IFlightDTO {
	String getFlightNumber();
	String getDeparturePort();
	String getArrivalPort();
	LocalDateTime getDepartureTime();
	LocalDateTime getArrivalTime();
	
}
