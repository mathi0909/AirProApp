package com.airpro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.utils.AirProFilters.AirProFilter;

@Configuration
public class AirProConfiguration {
	
	@Bean
	public AirProFilter getAirProFilter() {
		return new AirProFilter();
	}
	
	@Bean
	public ResponseVO getResponseVO() {
		return new ResponseVO();
	}

}
