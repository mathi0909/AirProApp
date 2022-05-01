package com.airpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.airpro.common.constant.CommonConstant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title=CommonConstant.SWAGGER_API_TITLE,version=CommonConstant.SWAGGER_API_TITLE))
public class AirProApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AirProApp.class, args);
	}

}
