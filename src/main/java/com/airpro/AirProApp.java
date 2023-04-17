package com.airpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AirProApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AirProApp.class, args);
	}

}
