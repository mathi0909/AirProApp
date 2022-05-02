package com.airpro.common.entity.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@NotBlank(message = "{error.air.pro.flight.number.is.mandate}")
	private String flightNumber;
	@NotNull
	@NotBlank(message = "{error.air.pro.departure.port.is.mandate}")
	private String departurePort;
	@NotNull
	@NotBlank(message = "{error.air.pro.arrival.port.is.mandate}")
	private String arrivalPort;
	@NotNull
	@NotBlank(message = "{error.air.pro.flight.code.is.mandate}")
	private String flightCode;
	@NotNull
	private LocalDateTime departureTime;
	@NotNull
	private LocalDateTime arrivalTime;

}
