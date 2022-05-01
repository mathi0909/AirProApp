package com.airpro.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirProDefaultModelMapper {

	@Autowired
	ModelMapper modelMapper;
	
	/**
	* Convert Source object into destination object.
	*  
	* @param <S>
	* Source object which has the values.
	* @param <T>
	* Destination object to which the values needs to copied
	* @return
	* returns the target class by copying the field values from the source class.
	*/
	public <S,T> T mapObject(S source, Class<T> targetClass){
	return modelMapper.map(source, targetClass);
	}
	 
	/**
	 *
	 * @param <S> Source List which has the value
	 * @param <T> Destination List to which the values should be copied
	 * @return returns the target list by copying the field values from the source list.
	 */
	public <S, T> List<T>  mapList(List<S> source, Class<T> targetClass) {
	   return source
	     .stream()
	     .map(element -> modelMapper.map(element, targetClass))
	     .collect(Collectors.toList());
	}

	
}
