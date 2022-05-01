package com.airpro.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.airpro.common.utils.AirProFilters.AirProFilter;

@Configuration
public class AirProConfiguration {
	
	@Bean
	public AirProFilter getAirProFilter() {
		return new AirProFilter();
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
//	@Bean
//	public ResponseVO getResponseVO() {
//		return new ResponseVO();
//	}

}
