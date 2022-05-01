package com.airpro.common.utils.AirProFilters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.airpro.common.entity.model.Flights;

import lombok.extern.slf4j.Slf4j;

/**
 * <em> This class is responsible to frame the filter option based on the
 * request. AirProFilter will be used while querying the DB via JPA. By default
 * the filter will consider the current day. The filter will have start time
 * from 00:00:00 to 23:59:59.
 * 
 * Function overloading is done on filter() to have the different flavors of
 * filters. We can reuse this in the service layer to frame the JPA query. </em>
 * 
 * @author Mathi
 * @version 1.0
 *
 */

@Component
@Slf4j
public class AirProFlightFilters {

	AirProFilter airProFilter;

	AirProFlightFilters(AirProFilter airProFilter) {
		this.airProFilter = airProFilter;
	}

	/**
	 * This is the default filter which has the current day filter which ranges from
	 * 00:00:00 to 23:59:59
	 * 
	 * @return airProFilter
	 */
	public AirProFilter filter() {
		airProFilter.setStartDate(LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		airProFilter.setEndDate(LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));

		return airProFilter;
	}

	/**
	 * The overloaded function has the filter of current day which ranges from
	 * 00:00:00 to 23:59:59 along with the flight code.
	 * 
	 * @param flightCode 
	 * @return
	 */
	public AirProFilter filter(String flightCode) {
		airProFilter.setFlightCode(flightCode);
		airProFilter.setStartDate(LocalDate.now(ZoneId.of("UTC")).atStartOfDay());
		airProFilter.setEndDate(LocalDate.now(ZoneId.of("UTC")).atTime(LocalTime.MAX));
		return airProFilter;
	}

}
