package com.airpro.common.entity.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flights {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID flightId;
	private String flightNumber;
	private String departurePort;
	private String arrivalPort;
	private String flightCode;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;

}
