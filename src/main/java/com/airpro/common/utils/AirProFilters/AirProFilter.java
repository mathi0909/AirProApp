package com.airpro.common.utils.AirProFilters;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirProFilter {
	private String flightCode;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

}
