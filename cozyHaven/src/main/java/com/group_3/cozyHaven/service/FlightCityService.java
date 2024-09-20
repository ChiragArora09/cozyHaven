package com.group_3.cozyHaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.exception.InvalidIdException;
import com.group_3.cozyHaven.model.City;
import com.group_3.cozyHaven.model.Flight;
import com.group_3.cozyHaven.model.FlightCity;
import com.group_3.cozyHaven.repository.FlightCityRepository;

@Service
public class FlightCityService {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private FlightCityRepository flightCityRepository;

	public FlightCity addFlightAndCity(int flightid, int cityid, FlightCity flightCity) throws InvalidIdException {
		Flight flight = flightService.findById(flightid);
		City city = cityService.findById(cityid);
		
		flightCity.setFlight(flight);
		flightCity.setCity(city);
		
		return flightCityRepository.save(flightCity);
		
	}
	
}
