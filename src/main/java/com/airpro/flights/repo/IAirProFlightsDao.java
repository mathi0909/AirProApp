package com.airpro.flights.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airpro.common.model.Flights;

public interface IAirProFlightsDao extends JpaRepository<Flights, UUID>{
	
	List<Flights> findByDepartureTimeBetween(LocalDateTime startDate,LocalDateTime endDate);
	List<Flights> findByDepartureTimeBetweenAndFlightCodeIgnoreCase(LocalDateTime startDate,LocalDateTime endDate,String flightCode);
	Boolean existsByFlightCodeIgnoreCase(String flightCode);
}
