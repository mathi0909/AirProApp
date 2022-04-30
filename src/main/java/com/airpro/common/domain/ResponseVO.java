package com.airpro.common.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.airpro.common.model.Flights;

import lombok.Data;

@Component
@Data
public class ResponseVO {

	List<Flights> flights;
}
