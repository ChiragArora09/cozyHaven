package com.group_3.cozyHaven.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group_3.cozyHaven.dto.FlightCityInputDto;
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

	public List<FlightCity> addFlightAndCity(int flightid, List<FlightCityInputDto> f) throws InvalidIdException {
		List<FlightCity> flightCities = new ArrayList<>();
		System.out.println(flightid);
		Flight flight = flightService.findById(flightid);
		System.out.println(flight.getId());
		System.out.println(flight.getName());
        for (FlightCityInputDto dto : f) {
            FlightCity flightCity = new FlightCity();
            
            City city = cityService.findById(dto.getCity_id());
            
            flightCity.setFlight(flight);
            flightCity.setCity(city);
    		flightCity.setStopNumber(dto.getStop_number());
    		flightCity.setDistance(dto.getDistance());
    		flightCity.setArrival(dto.getArrival());
    		flightCity.setDeparture(dto.getDeparture());
           
            flightCities.add(flightCity);
        }
		
		return flightCityRepository.saveAll(flightCities);
		
	}

	public void updateFlightAndCity(int flightid, List<FlightCityInputDto> flightCityInputDto) throws InvalidIdException {
		Flight flight = flightService.findById(flightid);
        for (FlightCityInputDto dto : flightCityInputDto) {
        	if(dto.getId() != null) {
        		FlightCity flightCity = flightCityRepository.findById(dto.getId()).get();
        		City city = cityService.findById(dto.getCity_id());
        		
        		flightCity.setArrival(dto.getArrival());
        		flightCity.setDeparture(dto.getDeparture());
        		flightCity.setCity(city);
        		flightCity.setDistance(dto.getDistance());
        		flightCity.setStopNumber(dto.getStop_number());
        		
        		flightCityRepository.save(flightCity);
        	}else if(dto.getId() == null) {
        		FlightCity flightCity = new FlightCity();
        		City city = cityService.findById(dto.getCity_id());
        		
        		flightCity.setFlight(flight);
                flightCity.setCity(city);
        		flightCity.setStopNumber(dto.getStop_number());
        		flightCity.setDistance(dto.getDistance());
        		flightCity.setArrival(dto.getArrival());
        		flightCity.setDeparture(dto.getDeparture());
        		
        		flightCityRepository.save(flightCity);
        	}

        }
	}
	
}
