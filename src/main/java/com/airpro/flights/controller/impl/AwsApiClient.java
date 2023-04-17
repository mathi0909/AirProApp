package com.airpro.flights.controller.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.airpro.common.model.ChangeBatch;

@Component
@FeignClient(name="awsapi", url="https://bgzmzvrori.execute-api.ap-south-1.amazonaws.com/dev" )
public interface AwsApiClient {

	@GetMapping(path="/test") 
	public String getToken(@RequestHeader("Authorization") String token);
	
	@PostMapping(path="/cognito") 
	public String updateDns(@RequestHeader("Authorization") String token,@RequestBody ChangeBatch changeBatch);
 
}
