package com.airpro.flights.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airpro.common.dto.IFlightDTO;
import com.airpro.common.model.Flights;

public interface IAirProFlightsDao extends JpaRepository<Flights, UUID> {

//	@Query("SELECT new com.airpro.common.dto.FlightDTO(flight.flightNumber,flight.departurePort,flight.arrivalPort,flight.departureTime,flight.arrivalTime)"
//			+ " FROM Flights flight where flight.departureTime between :startDate and :endDate ")
	List<IFlightDTO> findByDepartureTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<IFlightDTO> findByDepartureTimeBetweenAndFlightCodeIgnoreCase(LocalDateTime startDate, LocalDateTime endDate,
			String flightCode);

	Boolean existsByFlightCodeIgnoreCase(String flightCode);
}
