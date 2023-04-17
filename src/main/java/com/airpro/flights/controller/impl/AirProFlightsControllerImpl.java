package com.airpro.flights.controller.impl;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airpro.common.domain.ResponseVO;
import com.airpro.common.model.ChangeBatch;
import com.airpro.common.model.Changes;
import com.airpro.common.model.Flights;
import com.airpro.flights.controller.IAirProFlightsController;
import com.airpro.flights.service.IAirProFlightService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AirProFlightsControllerImpl implements IAirProFlightsController {
	
	@Autowired
	AwsApiClient aws;
	
	IAirProFlightService airProFlightService;
	public AirProFlightsControllerImpl(IAirProFlightService airProFlightService) {
		this.airProFlightService = airProFlightService;
	}

	@GetMapping("/flights")
	public ResponseVO flights() {
		 log.info("Enter flights ");
		return  airProFlightService.flights();
	} 
	
	@GetMapping("/flights/{flightCode}")
	public ResponseVO flights(@PathVariable String flightCode) {
		 log.info("Enter flights ");
		return  airProFlightService.flights(flightCode);
	} 
	
	@PostMapping("/flights")
	public Flights flights( @RequestBody Flights flight) {
		 log.info("Enter flights ");
		return  airProFlightService.flights(flight);
	} 

	@GetMapping("/token/{token}")
	public String fetToken(@PathVariable String token) {
		 log.info("Enter flights ");
		 
		 
		 Map<String, String> authParams = new LinkedHashMap<String, String>() {{
	            put("USERNAME", "testuser");
	            put("PASSWORD", "User@1234");
	        }};
	        
	        AWSCognitoIdentityProvider client = AWSCognitoIdentityProviderClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials("AKIATUYGI6G5W75XFYYN", "VglNKRuzT0j/9Nmk/VyrUKhf42LnytlqxpKok+LF","IQoJb3JpZ2luX2VjECQaCmFwLXNvdXRoLTEiSDBGAiEAkEfDzEZHArqr7Ybb6uT6J8MdQgHWE+ouBwfdvXg7vgoCIQDTxQ37vCGyIRtj841cflC2rf2sFvhT6lOctPuOS71U9iroAggdEAAaDDI1MDczMTg4NDk4NyIMY0Ta9RevSnKkBCGTKsUCFKaEW6DWpzbda+yAzpgRqWhOEsk+B1ImjO8Pz52KzHuOdi/xIMDXebfEYVg3nMFooDoXGcenKpXFYa5gMg9R1RFhlOp6PS6Klv1TnX6MmcuovaqtqT7IHr4M9wnbQ9ak/35g66Q/30qjLkSFje5AD66TtoUP3s3cGNXpU/Lq7fzRX+41J/fkxZOG0X2K4nHJPGbsgBLbPnh4ZqjuqWa5Ggp9nyFbCG2Zg//f0vSUJ7EokVM61hvYxUfJjx0NpA7dJn+nUHNtNZwIgZN608Wox2IJxJyhrBEQBFad4t6P6RKrEwfe/UT9657aYcMl6XO8DJK2fUs9jOFlU8cUECqHSe+uj/IWFxyh/Q+0qXCx0jLJk7Mp2jHwltI0F5zHoP/Krno6iivvKlMn5ABbJhj0NbBW3R9XcRGYqVaQJR+FrDnQi+M1pjCMiOyhBjqmAZ4njF8noh3hEj53hVkRqX30d9nKaPCvYfMmjT8tztIkrby0DZAFwFVuy2GB/uPXz7FaPkGCIcfMYgMl4vqPSd1i3SmNLrO1gzwr4Ce5YjPqEqE/kXoGDaCWYnpMGwuccOhMJzMTxqky676KJfSNy63qk/0iYhbOGFrDRVFoZoFXmskVIuQj2ZYplfhSE9NBevJhXAzk4Cdbtn+dps9k+HekEuH1d8M=")))
            .withRegion(Regions.AP_SOUTH_1)
            .build();
	        
	        AWSCognitoIdentityProvider client1 = AWSCognitoIdentityProviderClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIATUYGI6G5W75XFYYN", "WZZCl6NPPUCBXruzA9Oin6NDzoFkWnJCH8Xw3R0B")))
	                .withRegion(Regions.AP_SOUTH_1)
	                .build();

	        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
	                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
	                .withUserPoolId("ap-south-1_fGZIdjBCA")
	                .withClientId("73qa83cd04icmsoc9ienedk2oj")
	                .withAuthParameters(authParams);
	        
	        AdminInitiateAuthResult authResult = client1.adminInitiateAuth(authRequest);
	        AuthenticationResultType resultType = authResult.getAuthenticationResult();
	          
	        ChangeBatch req = ChangeBatch.builder()
	        	.changes(Arrays.asList(Changes.builder()
	        			.action("UPSERT")
	        			.domain("java-gateway-dyndns.mypocbox.com")
	        			.TTL("34")
	        			.ip("195.234.156.4")
	        			.type("A")
	        			.build()))
	        .build();
	        
	       
		 
		   
		    new LinkedHashMap<String, String>() {{
	            put("idToken", resultType.getIdToken());
	            put("accessToken", resultType.getAccessToken());
	            put("refreshToken", resultType.getRefreshToken());
	            put("message", "Successfully login");
	        }};
	        
	        return  aws.updateDns(resultType.getIdToken(), req);
	} 
}
